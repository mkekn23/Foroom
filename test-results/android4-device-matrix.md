

| | Device 1 | Device 2 |
|---|---|---|
| Device model / AVD name | Pixel 7 Pro | Medium Phone |
| Android version / API level | Android 13 / API 33 | Android 14 / API 34 |
| Screen resolution | 1440 x 3120 px (560 dpi) | 1080 x 2400 px (420 dpi) |
| Emulator or physical device | Emulator (Android Virtual Device) | Emulator (Android Virtual Device) |

## Results

| Scenario | Test method | Pixel 7 Pro  | Medium Phone  |
|---|---|---|---|
| 1. Send a message in the `johnWeek` chat | `userASendsMessageInJohnWeekChat_messageIsShownAndKeptAfterReopen` | Pass (22.8 s) | Pass (11.0 s) |
| 2. Send a question in the full-name chat | `userASendsQuestionInFullNameChat_questionIsShownInConversation` | Pass (10.4 s) | Pass (9.0 s) |
| 3. Continue a conversation using another account | `userBRepliesToUserAGreetingInSharedChat_userAThenSeesTheReply` | Pass (67.6 s) | Pass (60.8 s) |
