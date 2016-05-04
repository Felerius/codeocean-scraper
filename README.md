# codeocean-scraper
Batch download student submission from codeocean

## Setup

- Clone project
- Install [gradle](http://gradle.org)
- Run `gradle shadowJar`
- Project is now build as a self-contained jar file in `build/libs`

## Running

Execute with `java -jar path/to/jar targetDirectory statisticsPageUrl sessionKey`.

Parameter explanation:
- *targetDirectory:* All downloaded submissions will be saved under this directory. The directory must already exist.
- *statisticsPageUrl:* Url to the statistics page of the exercise you want to download the submissions for.
- *sessionKey:* Session key for authentication. Obtained by logging into CodeOcean in your browser, then copying the content of the `_hands-on-programming_session` cookie.

## Result

After execution the target directory will contain a subdirectory for each user.
These subdirectories can contain the following content:

- `name.java`: Last submitted version.
- `name - ASSESSED AFTER SUBMIT.java`: Version that was scored (but not submitted) after the last submitted version.
- `name - ASSESSED ONLY.java`: Last assessed version, if no version was ever submitted.
- `name - NO ASSESS OR SUBMIT.java`: No version was ever scored or submitted. Contains the last autosave.
- `name - TOP SCORE.java`: Version with the highest score. Only included if not matched by any of the other criteria. If multiple such versions exists, the last one is chosen.
- `error.txt`: Created if the server responded with an error when trying to download the submission page. Contains error details.

All `*.java` files will also contain an initial comment with the url to the submission page, and the score of the submission.
