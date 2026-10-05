# Sonar + Jenkins + Coverage Practice Lab

A small Employee Management backend (Controller -> Service -> Repository -> DTO -> Entity -> Exception,
plus a Util class) with REAL issues deliberately planted in it, so you can practice finding and
fixing exactly the kind of things SonarLint/SonarQube flag at work.

## Step 1 - Install SonarLint in IntelliJ (no server needed for this)
1. IntelliJ -> Settings/Preferences -> Plugins -> Marketplace.
2. Search "SonarLint" -> Install -> Restart IntelliJ.
3. Open this project. SonarLint will underline issues directly in the editor (like a spell-checker,
   but for code quality/bugs/security).

## Step 2 - Open this project
Open this folder (`SonarJenkinsCoveragePractice`) in IntelliJ as a Maven project
(IntelliJ will detect `pom.xml` automatically and download dependencies).

## Step 3 - Find the planted issues
Don't look at the source comments as answers - most planted issues have NO comment pointing them out
on purpose. Let SonarLint underline them for you, then come back here and we'll fix each one together,
one Sonar rule category at a time:
- Bugs (things that can crash or behave wrong) - e.g. comparing objects incorrectly, swallowed exceptions.
- Code Smells (not broken, but bad practice / hard to maintain) - e.g. magic numbers, deep nesting,
  public mutable fields, unused variables/parameters, missing private constructor on a utility class.
- Security Hotspots (not relevant much in this tiny lab, but it's a real Sonar category at work).

Rough count planted across this project: **10+ issues** spread mainly in `SalaryCalculator.java`,
`EmployeeServiceImpl.java`, `EmployeeController.java`, and `Employee.java`.

## Step 4 - Run tests with coverage (JaCoCo)
In IntelliJ: right-click `src/test/java` -> "Run All Tests with Coverage".
Or from a terminal in this folder: `mvn clean test`
Then open `target/site/jacoco/index.html` in a browser to see the real coverage %% report.

Coverage will look LOW on purpose - `calculateBonus`, `calculateHike`, and parts of
`EmployeeServiceImpl` have zero or partial tests. Writing more JUnit tests to cover those
branches is part of the exercise.

## Step 5 - Jenkins (coming next)
Once the Sonar issues are fixed and coverage is raised, we'll set up a local Jenkins instance
(`java -jar jenkins.war`) with a simple pipeline job that runs `mvn clean verify` on this project
automatically - the same kind of job your work pipeline runs.
