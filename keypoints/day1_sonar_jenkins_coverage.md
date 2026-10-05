# Key Points - Sonar / Jenkins / Coverage (Day 1)

📌 **SonarLint vs SonarCloud**
SonarLint = checks YOUR code, live, in IntelliJ, no server.
SonarCloud = checks the TEAM's code, on a shared dashboard, via a pipeline.
Trick: Lint = your desk. Cloud = the whole team's office.

📌 **Automatic Analysis vs GitHub Actions (CI-based)**
Automatic = SonarCloud scans code only, no real coverage.
GitHub Actions = builds + runs tests first, so coverage is real.
Trick: no test run = no real coverage number, ever.

📌 **Severity levels**
Blocker = will break production / security risk (e.g. hardcoded password).
Critical = likely bug (e.g. possible NullPointerException).
Major = real but less urgent (e.g. overly complex method).
Minor = small cleanliness issue (e.g. leftover TODO).

📌 **String == vs .equals()**
== checks "same object in memory." .equals() checks "same content."
Example: `employee.department == "ENGINEERING"` -> use `.equals()` instead.
Trick: == asks "are you the same person?" .equals() asks "do you look the same?"

📌 **Unclosed resources (FileWriter, Scanner, etc.)**
Opening a file/stream without closing it leaks a handle every time it runs.
Fix: use try-with-resources so Java closes it automatically.

📌 **Secrets vs hardcoded credentials**
Never put passwords/tokens directly in code (Blocker-level security issue).
Store them as a GitHub Secret (like SONAR_TOKEN) and reference via `${{ secrets.X }}`.

📌 **Magic Numbers**
A raw number dropped into code with no explanation of what it means or why that value.
Fix: give it a name - `private static final double TOP_RATING_THRESHOLD = 4.5;`
Trick: a magic number is a number with no name tag. Name it (constant) and the magic goes away.
