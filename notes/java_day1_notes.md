# Java Day 1 Notes - JVM/JDK/JRE, Compile/Build/Run

📌 **JDK vs JRE vs JVM**
JDK = dev studio (build it). JRE = console (run it). JVM = the console's processor (actually executes it).
Example: `javac Main.java` (JDK's compiler) -> `Main.class` -> JVM runs it.
Trick: JDK contains JRE contains JVM - each layer adds more on top of the one below.

📌 **Compile vs Build vs Run**
Compile = translate `.java` (human code) into `.class` (bytecode).
Build = the whole prep (compile + package + gather dependencies).
Run = JVM actually executes the bytecode, line by line.
Example: `javac Main.java` (compile) -> `java Main` (run).
Trick: Compile = Translate. Build = Package the whole meal. Run = Chef performs it live.

📌 **Compiled or Interpreted?**
Java is BOTH. Compiled once to bytecode, then the JVM's JIT compiler turns that into
native machine code while running.
Example: same `.class` file runs unmodified on Windows, Mac, or Linux - each JVM translates
it for its own OS.
Trick: "Write once, run anywhere" exists because of this two-step process.

📌 **IntelliJ vs JVM - NOT the same thing**
IntelliJ (or Eclipse) = an IDE, a separate program (`idea64.exe`) that is just a convenient
editor with buttons. JVM = the real engine (`java.exe`), a totally separate running process
that actually executes the code.
Example: clicking the green Run button just makes IntelliJ run `javac` then `java` for you
behind the scenes - the exact same thing you'd type yourself in a terminal.
Trick (user's own analogy): Manager (IntelliJ) delegates to Employee (JVM) who does all the
real work, then Manager just presents the result.
Proof: open Task Manager while running code - you'll see TWO separate processes,
`idea64.exe` AND `java.exe`.

📌 **Compile-time error vs Runtime error**
Compile-time error = broken grammar, program never even starts (example: missing semicolon).
Runtime error (Exception) = grammar was fine, something breaks while running
(example: `5/0` throws `ArithmeticException`).

📌 **Magic Numbers (code smell)**
A raw number dropped into code with no explanation of what it means or why that value.
Example: `if (rating >= 4.5) return salary * 0.18;` - what IS 4.5? What IS 0.18?
Fix: give it a name - `private static final double TOP_RATING_THRESHOLD = 4.5;`
Trick: a magic number is a number with no name tag. Name it (constant) and the magic goes away.

---

## Interview Q&A quick-fire

**Q: Is Java compiled or interpreted?**
A: Both - compiled to bytecode once, then the JVM's JIT compiler translates that bytecode
into native machine code at runtime.

**Q: JDK vs JRE vs JVM?**
A: JDK is the full dev kit (compiler + tools). JRE is what's needed to run an app (JVM +
libraries). JVM is the actual engine inside JRE that executes bytecode. JDK contains JRE
contains JVM.

**Q: Is an IDE like IntelliJ the same as the JVM?**
A: No. IntelliJ is a separate development tool. When I click Run, IntelliJ runs `javac`
then `java` for me - the JVM is a different, independent process doing the real execution.

**Q: Why avoid magic numbers?**
A: They have no documented meaning, so if the business rule changes, there's no single
place to update and no context for why that value was chosen. Extracting them into
named constants documents intent and gives one source of truth.
