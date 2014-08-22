Beat-to-the-Beat
================

Repo for TDA367-project


Members: 
Malin Thelin
Hampus Dahlin
Björn Hedström
Pontus Eriksson
================
Known issues:
GitInspector might be off since we've refactored ALOT of code and moved whole classes. We were all over 701 rows the first time.

Both the SongUploadPanel and the ScorePanel has a accompanying class called SongUploadFunctionality respectivly ScoreFunctionality
These classes were not refactored wich means that SonguploadFunctionality is depeded on the SongUploadPanel and the same for ScorePanel/ScoreFunctionality
This is wrong and should be refactored.  