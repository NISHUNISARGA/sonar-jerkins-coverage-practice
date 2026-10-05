# Sonar + GitHub Actions Setup Guide (Full Steps)

This file explains, in simple steps, everything needed to go from "a plain project" to
"a project that automatically checks code quality on every push." Follow it top to bottom.

## 1. What this project is

A small Employee Management backend (Controller, Service, Repository, DTO, Entity, Util).
It has real issues placed in it on purpose, so we can practice finding and fixing the
same kind of problems a real company's SonarQube would flag.

## 2. Open the project in IntelliJ

Open this folder (`SonarJenkinsCoveragePractice`) in IntelliJ. It has a `pom.xml`, so
IntelliJ will recognize it as a Maven project automatically and download what it needs.

## 3. Install SonarLint (checks code live, inside IntelliJ, no server needed)

1. IntelliJ -> Settings/Preferences -> Plugins -> Marketplace.
2. Search "SonarLint" -> Install -> Restart IntelliJ.
3. Open any `.java` file in this project. SonarLint underlines problems directly in the editor.

This is your personal, instant checker. It does NOT need internet or a server. It is
different from SonarCloud (step 5 onward), which is the shared, team-level checker.

## 4. Put the project on GitHub

1. On github.com, click "+" -> "New repository".
2. Give it a name. Keep it Public. Do NOT check "Add a README/.gitignore/license"
   (this project already has its own files).
3. Create it, and copy the `.git` URL it shows you.
4. In a terminal, inside this project folder:
   ```
   git init
   git branch -M main
   git add .
   git commit -m "Initial commit"
   git remote add origin <paste the .git URL here>
   git push -u origin main
   ```
5. The first push may open a browser window asking you to log in to GitHub and "Authorize"
   a tool called Git Credential Manager. This is normal and safe - approve it.

**Common mistake:** typing the repo name slightly wrong (example: "jerkins" vs "jenkins")
between what you created on GitHub and what you tell git to push to. If you get
"remote: Repository not found", check the exact spelling first.

## 5. Connect the project to SonarCloud (the shared/team-level checker)

1. Go to sonarcloud.io -> Log in with GitHub -> approve the SonarCloud GitHub App.
2. Click "+" -> "Analyze new project" -> pick your repository.
3. Choose "Maven" when it asks what kind of project this is.

## 6. Pick HOW SonarCloud will scan your code

On the project's "Analysis Method" page, you'll see:
- **Automatic Analysis** - SonarCloud scans your code by itself. Easiest, but it CANNOT
  run your tests, so it never shows real code coverage.
- **With GitHub Actions** - a pipeline builds and tests your code first, THEN sends the
  results to SonarCloud. More setup, but gives real coverage numbers. **Use this one.**

Steps for "With GitHub Actions":
1. If Automatic Analysis is turned ON, turn it OFF first (they cannot both be on).
2. Create a GitHub secret:
   - Go to your repo on github.com -> Settings tab (the repo's own Settings, not your
     account Settings) -> left sidebar -> Secrets and variables -> Actions ->
     "New repository secret".
   - Name: `SONAR_TOKEN` (must be exactly this, capital letters, with underscore).
   - Secret/Value: paste the token SonarCloud showed you.
   - Click "Add secret".
3. Choose "Maven" as the build tool when asked.
4. SonarCloud may offer "Turn on automatic configuration" - this creates the pipeline
   file for you. You can use that, or add your own `.github/workflows/build.yml` file
   (this project already has one - see below).

## 7. The pipeline file: `.github/workflows/build.yml`

This file tells GitHub what to do every time you push code:
1. Download the code (`checkout`).
2. Install Java 17 (`setup-java`).
3. Run `mvn clean verify` - this compiles the code, runs the tests, and creates a
   coverage report (using JaCoCo, already configured in `pom.xml`).
4. Run the Sonar scanner, which sends the results (bugs, code smells, coverage) to
   SonarCloud.

You do not need to run anything manually - this file runs automatically on every push
to the `main` branch.

## 8. Check if it worked

1. Go to `https://github.com/<your-username>/<your-repo-name>/actions`.
2. Click the latest run. Green checkmark on every step = it worked.
   Red X on a step = something failed - open that step to read the error message.
3. Go to sonarcloud.io -> My Projects -> your project.
4. Look at the "Summary" tab for the overall numbers (coverage %, bugs, code smells).
5. Look at the "Issues" tab for the actual list of problems found, each with a
   severity: Blocker, Critical, Major, or Minor.
6. Look at the "Security Hotspots" tab separately - some security-related findings
   (like hardcoded passwords) show up there, not in the regular "Issues" tab.

## 9. Severity meanings (simple version)

- **Blocker**: must fix - this can cause real production problems or security risk.
- **Critical**: very likely to cause a bug (example: possible NullPointerException).
- **Major**: a real quality problem, but less urgent (example: code too complex to read).
- **Minor**: small cleanliness problem (example: unnecessary comparison, leftover TODO).

## 10. If some rules aren't showing up (like "Magic Number")

SonarCloud's default rule set ("Sonar way") is deliberately conservative - it does not
turn on every possible rule, to avoid noise. To get more rules active:

1. Go to your project -> Administration -> Quality Profile.
2. For Java, change the profile from the default to **"Sonar way Comprehensive"**
   (a broader built-in rule set with more code smells active).
3. Re-run the pipeline (push any small change, or use "Re-run jobs" on the latest
   GitHub Actions run) so analysis runs again with the new profile.
4. If a specific rule still doesn't show (some rules are not in ANY built-in profile),
   go to the organization's "Quality Profiles" page, copy a built-in profile into an
   editable one, use "Activate More Rules" to search for and turn on that specific rule,
   then set that new profile as the project's active profile and re-run again.

## 11. Fixing issues and watching numbers improve

After fixing a bug or adding a test:
```
git add .
git commit -m "describe what you fixed"
git push
```
This automatically re-triggers the pipeline. Refresh the SonarCloud dashboard after a
couple of minutes to see the issue count go down and/or coverage go up.

## 12. What's intentionally planted in this project (for reference)

- `SalaryCalculator.java` - public mutable static field, missing private constructor
  on a utility class, deeply nested if-else (high cognitive complexity), magic numbers.
- `EmployeeServiceImpl.java` - unused local variable, empty catch block, `System.out`
  instead of a logger, comparing Strings with `==` instead of `.equals()`.
- `EmployeeController.java` - unused method parameter.
- `Employee.java` - public mutable fields instead of private + getters/setters.
- `ReportService.java` - hard-coded password (Security Hotspot), unclosed `FileWriter`
  (resource leak), null checked then dereferenced anyway (possible NullPointerException),
  redundant boolean comparison, a leftover TODO comment.
- `EmployeeServiceTest.java` - only partially covers the code on purpose, so there is
  something real to improve by writing more tests.

This is the answer key. Try to fix things using what SonarCloud shows you first, and
only check this list if you get stuck.
