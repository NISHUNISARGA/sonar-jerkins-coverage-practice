# Interview Q&A - Sonar / Jenkins / Coverage (Day 1)

**Q: What's the difference between SonarLint and SonarQube/SonarCloud?**
A: SonarLint runs locally in the IDE for instant personal feedback with no server.
SonarQube/SonarCloud is the shared server/dashboard the whole team's code reports to,
enforcing a Quality Gate that can block merges.

**Q: Does 100% code coverage mean no bugs?**
A: No - coverage only measures what code *ran* during tests, not what was *verified
correct*. A test can execute a line without asserting anything meaningful.

**Q: Why is comparing Strings with `==` risky?**
A: `==` checks object reference identity, not content. Two Strings with identical text
aren't guaranteed to be the same object unless both are pooled literals - content from
user input or a database can have equal text but fail `==`.

**Q: How do you handle secrets (like API tokens) in a CI/CD pipeline?**
A: Store them as encrypted secrets in the CI platform (e.g., GitHub Secrets) and
reference them via environment variables in the pipeline - never commit them directly
into source code.

**Q: What's a good code coverage target?**
A: It depends on the team, but 70-80% line coverage on business logic is a common bar.
I'd rather have 70% coverage with meaningful assertions than 100% coverage with tests
that call methods and check nothing.

**Q: Why can't you compile Java code with just JRE installed?**
A: JRE only includes the JVM plus runtime libraries needed to execute already-compiled
bytecode - it deliberately leaves out `javac`, since a regular user running an app
doesn't need to build one. JDK is JRE plus that compiler and other dev tools.

**Q: What's a magic number and why avoid it?**
A: It's a hardcoded literal value with no explanation of its meaning or purpose. If the
business rule changes, there's no single place to update it and no context for why that
value was chosen. Extracting it into a named constant documents intent and gives one
source of truth.
