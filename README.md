# opap_api
**Version 1.0.0**

A spring application that stores to database the numbers that come from the fron end and shows them
The API used belongs to OPAP. I fetch the last 99 games of KINO and get their number results and add them to the databse with their "popularity" (number of times they showed up) in different games.
With AJAX at front end i post and fetch the data to and from the server and show it with a basic layour using bootstrap table.
Used rest controllers on spring to give and get json formatted objects

This needs to be done once every 48 hours because 99 games take to finish 48 hhours

I have a timer for the last posted time and i wont let new posts before the timer ends 

At the front end i show the timer with an alert box
