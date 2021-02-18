Hird-readme.txt
Casey Hird
C12932552

HOW TO RUN THE APP:
Since you are viewing this readme file, we’ll assumer you have already unzipped the project folder.
Next, open the android project in android studio.
Click the hammer icon at the top of the screen to build the project.
Next, in the drop-down menu near the hammer icon, select the Pixel 2 emulator.
Then click the play icon in android studio to launch a Pixel 2 emulator.
Once the emulator has started, click on the Hird_HW1 app and use the app.

DESIGN CHOICES:
The color scheme of softer blues is used to give the game an overall relaxing experience,
since we are assuming that most users playing a game of Pig Dice are not interested in
a bright, aggressive UI. The particular colors used where chosen using https://coolors.co/.
Another important design choice was button placement, both in the main activity and the
game activity. In both cases, buttons were placed toward the bottom of the screen to make
them more accessible to users with a single hand on their device. Additionally, the the game
activity, the "Bank Score" button is placed higher up (further from the users thumb) Since
it is a slightly more destructive action, and would be less convenient to click accidentally.
Also, the dice image in the game is placed near the right of the screen so that in future
versions, players holding their device with their right hand might be able to roll by
clicking or flicking on the die (once we implement that functionality).
Note that the text in buttons is in ALL CAPS, and that the background color of buttons is
different from that in areas with normal text (like the scoreboard in the game activity).
This clearly differentiates buttons as items with which the user can interact.

BANKER STRATEGY:
The banker strategy is simple, but intuitive. Without using too much information about
the current situation of the game, the Banker of course Banks their score if the round
score is sufficient to win the game, and other wise banks only if the round score is at
or above 20. This number was chosen based on the anlysis in
https://www.maplesoft.com/applications/view.aspx?SID=34780&view=html, which demonstrates
that the expected value of continued rolling is positive white the accumulated total
is less than 20. While the strategy is not optimal, it does give the banker a legitimate
chance of winning.

SOURCES:
The pig drawable is from Icons made by https://www.freepik.com from https://www.flaticon.com/.
(This citation is copied from their site).
