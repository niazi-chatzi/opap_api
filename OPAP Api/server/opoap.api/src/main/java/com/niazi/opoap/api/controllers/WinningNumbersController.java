package com.niazi.opoap.api.controllers;

import com.niazi.opoap.api.entities.WinningNumber;
import com.niazi.opoap.api.services.UpdateLogService;
import com.niazi.opoap.api.services.WinningNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class WinningNumbersController {

    private final WinningNumbersService winningNumbersService;
    private final UpdateLogService updateLogService;

    public WinningNumbersController(WinningNumbersService winningNumbersService,
                                    UpdateLogService updateLogService) {
        this.winningNumbersService = winningNumbersService;
        this.updateLogService = updateLogService;
    }

    @CrossOrigin
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, Object> getWinningNumbers() {
        return new HashMap() {{ put("2 days passed?", updateLogService.isUpdatable()); put("message",
                updateLogService.getCountDown()); }};
    }

    @CrossOrigin
    @RequestMapping(value = "/winningNumbers", method = RequestMethod.GET)
    public Map<String, Object> getWinningNumbers(HttpServletResponse response,
                                                 @RequestParam(value = "sort", required = false) Boolean sort) {
        List<WinningNumber> winningNumbersList = winningNumbersService.getAll();

        if(winningNumbersList.isEmpty()) {
            response.setStatus(404);
            return new HashMap() {{ put("error", "winningNumbers not found"); }};
        }

        if(sort != null && sort) {
            response.setStatus(200);
            return new HashMap<>() {{
                put("data", winningNumbersService.getAllSorted());
            }};
        }

        response.setStatus(200);
        return new HashMap<>() {{ put("data", winningNumbersList); }};
    }

    @CrossOrigin
    @RequestMapping(value = "/winningNumbers/{id}", method = RequestMethod.GET)
    public Map<String, Object> getWinningNumberById(@PathVariable Integer id, HttpServletResponse response) {
        Optional<WinningNumber> winningNumber = winningNumbersService.getById(id);

        if(winningNumber.isEmpty()) {
            response.setStatus(404);
            return new HashMap() {{ put("error", "winningNumber not found"); }};

        }

        response.setStatus(200);
        return new HashMap<>() {{ put("data", winningNumber.get()); }};
    }

    @CrossOrigin
    @RequestMapping(value = "/winningNumbers", method = RequestMethod.POST, consumes = {"application/json"})
    public Map<String, Object> postWinningNumbers(@RequestBody List<WinningNumber> requestObj,
                                               HttpServletResponse response) {
        List<WinningNumber> postedList = winningNumbersService.post(requestObj);

        if(postedList.isEmpty()) {
            response.setStatus(400);
            return new HashMap() {{ put("error", "bad operation"); }};
        }

        response.setStatus(200);
        return new HashMap<>() {{ put("message", "ok"); }};
    }

    @CrossOrigin
    @RequestMapping(value = "/winningNumbers", method = RequestMethod.PUT, consumes = {"application/json"})
    public Map<String, Object> putWinningNumbers(@RequestBody List<WinningNumber> requestObj,
                                                    HttpServletResponse response) {
        if(!updateLogService.isUpdatable()) {
            response.setStatus(400);
            return new HashMap<>() {{ put("message", updateLogService.getCountDown());}};
        }

        List<WinningNumber> updatedNumbers = winningNumbersService.update(requestObj);
        if(updatedNumbers.isEmpty()) {
            response.setStatus(500);
            return new HashMap<>() {{ put("message", "Internal Server ERROR"); }};
        }

        response.setStatus(200);
        return new HashMap<>() {{ put("data", updatedNumbers); }};
    }
}
