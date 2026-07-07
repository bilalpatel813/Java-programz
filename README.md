# ☕ Java — The Complete Developer Handbook

> Not just a reference — a hands-on guide. Every concept comes with working code, a practice drill, and a real-world context so you understand not just *what* Java does, but *why* it works the way it does.

---

```
  [ Your .java source code ]
          ↓  javac compiles
  [ Bytecode (.class file) ]
          ↓  JVM interprets / JIT-compiles
  [ Machine Code — runs anywhere ]
```

**Write Once, Run Anywhere** — the JVM (Java Virtual Machine) sits between your code and the operating system, making the same `.class` file run on Windows, Linux, and macOS without changes. The JDK (Java Development Kit) is what you install to compile and run Java. The JRE (Java Runtime Environment) is what end-users need to just run it.

---

## 📋 What's Inside

| Chapter | Topic |
|---------|-------|
| 01 | Variables & Data Types |
| 02 | Operators |
| 03 | Conditions |
| 04 | Loops |
| 05 | Methods |
| 06 | Arrays |
| 07 | Strings |
| 08 | Classes & Objects |
| 09 | Constructors |
| 10 | Access Modifiers & Packages |
| 11 | Static & Final |
| 12 | Inheritance |
| 13 | Polymorphism |
| 14 | Abstraction |
| 15 | Encapsulation |
| 16 | Inner Classes |
| 17 | Enums |
| 18 | Generics |
| 19 | Collections Framework |
| 20 | Iterators & forEach |
| 21 | Exception Handling |
| 22 | File I/O |
| 23 | Multithreading |
| 24 | Lambdas & Functional Interfaces |
| 25 | Stream API |
| 26 | Optional |
| 27 | Records & Sealed Classes |
| 28 | JDBC & Databases |
| 29 | Design Patterns |
| 30 | JVM Internals & Memory |

---

# Chapter 01 — Variables & Data Types

## The Java Type System

Java is **statically typed** and **strongly typed** — every variable must be declared with a type, and that type never changes. The type system is split into two worlds:

```
Java Types
├── Primitive (stored directly in memory — stack)
│   ├── byte, short, int, long      (integers)
│   ├── float, double               (decimals)
│   ├── char                        (single character)
│   └── boolean                     (true / false)
└── Reference (pointer to object on the heap)
    ├── String
    ├── Arrays
    ├── Classes
    └── Interfaces
```

## Primitive Types at a Glance

```java
byte   b = 127;               // -128 to 127                      (8-bit)
short  s = 32767;             // -32,768 to 32,767               (16-bit)
int    i = 2_147_483_647;     // ~2.1 billion                    (32-bit) ← default integer
long   l = 9_223_372_036_854_775_807L; // huge — needs 'L' suffix (64-bit)
float  f = 3.14f;             // single-precision decimal — needs 'f' suffix (32-bit)
double d = 3.141592653589793; // double-precision decimal — DEFAULT for decimals (64-bit)
char   c = 'A';               // a single Unicode character       (16-bit)
boolean flag = true;          // true or false only               (1-bit concept)
```

## Variable Declarations

```java
// Declare, then assign
int age;
age = 25;

// Declare and assign in one line
String name = "Alice";

// var — local type inference (Java 10+, only for LOCAL variables)
var score = 100;          // compiler infers int
var message = "Hello";    // compiler infers String
// var is just syntactic sugar — the bytecode is identical to int score = 100;

// final — the constant of Java (value cannot be reassigned)
final double PI = 3.14159;
final String APP_NAME = "MyApp";
// PI = 3.0;             // ❌ Compilation error

// final variable doesn't have to be assigned at declaration
final int MAX;
MAX = 100;                // ✅ assigned exactly once — called "blank final"
```

## Type Conversion

```java
// Widening (implicit, safe — smaller → larger)
int x = 100;
long y = x;          // int → long (no cast needed)
double z = x;        // int → double (no cast needed)

// Narrowing (explicit cast required — may lose data)
double pi = 3.99;
int truncated = (int) pi;   // → 3  (decimal part DROPPED, not rounded)

long big = 1_000_000_000_000L;
int small = (int) big;      // data may overflow/corrupt!

// String ↔ Primitive conversions (very common)
int parsed  = Integer.parseInt("42");          // String → int
double dparsed = Double.parseDouble("3.14");   // String → double
boolean bparsed = Boolean.parseBoolean("true");

String fromInt = String.valueOf(42);           // int → String
String fromDouble = Double.toString(3.14);     // double → String
String concat = "" + 42;                       // quick but less explicit

// Wrapper classes — box primitives into objects (needed for collections)
Integer boxed = 25;            // autoboxing:  int → Integer (automatic)
int unboxed = boxed;           // unboxing: Integer → int (automatic)

Integer a = 127; Integer b = 127;
System.out.println(a == b);    // true  (JVM caches -128 to 127)

Integer c = 200; Integer d = 200;
System.out.println(c == d);    // false (different objects — use .equals()!)
System.out.println(c.equals(d)); // true ← always use this for objects
```

---

### 🔬 Practice Program 1-A — Unit Converter

**Goal:** Practice declaring different primitive types, doing arithmetic, and conversion.

```java
public class UnitConverter {
    public static void main(String[] args) {
        // Distance: feet to meters
        double feet = 5.9;
        double meters = feet * 0.3048;
        System.out.printf("%.1f feet = %.2f meters%n", feet, meters);

        // Temperature: Celsius to Fahrenheit
        double celsius = 100.0;
        double fahrenheit = (celsius * 9.0 / 5.0) + 32;
        System.out.printf("%.1f°C = %.1f°F%n", celsius, fahrenheit);

        // Data size: bytes to megabytes
        long bytes = 1_073_741_824L;        // 1 GB in bytes
        double megabytes = bytes / 1_048_576.0;
        System.out.printf("%,d bytes = %.1f MB%n", bytes, megabytes);

        // Overflow demonstration
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Max int: " + maxInt);
        System.out.println("Max int + 1: " + (maxInt + 1));  // wraps to negative!
    }
}
```

**Output:**
```
5.9 feet = 1.80 meters
100.0°C = 212.0°F
1,073,741,824 bytes = 1024.0 MB
Max int: 2147483647
Max int + 1: -2147483648
```

---

### 🔬 Practice Program 1-B — Student Grade Card

**Goal:** Use `var`, final, and type conversion together.

```java
public class GradeCard {
    public static void main(String[] args) {
        final String STUDENT_NAME = "Bilal";
        final int MAX_MARKS = 100;

        var mathMarks    = 88;
        var scienceMarks = 92;
        var englishMarks = 79;

        int total = mathMarks + scienceMarks + englishMarks;
        double percentage = (double) total / (MAX_MARKS * 3) * 100;
        char grade = percentage >= 90 ? 'A' : percentage >= 75 ? 'B' : 'C';

        System.out.println("=== " + STUDENT_NAME + "'s Report Card ===");
        System.out.println("Math:    " + mathMarks);
        System.out.println("Science: " + scienceMarks);
        System.out.println("English: " + englishMarks);
        System.out.println("Total:   " + total + "/" + (MAX_MARKS * 3));
        System.out.printf("Percent: %.2f%%%n", percentage);
        System.out.println("Grade:   " + grade);
    }
}
```

---

### 🌍 Real-World Example — Banking System Variable Declarations

In a real banking application, variable types are chosen very carefully:

```java
public class BankAccount {
    // long for account number — int would overflow
    long accountNumber = 1234567890123456L;

    // double is WRONG for money — use BigDecimal to avoid floating-point errors!
    // double balance = 1000.10 + 2000.20;  ← gives 3000.3000000000003 (BUG!)
    java.math.BigDecimal balance = new java.math.BigDecimal("3000.30");  // ✅ exact

    // int for age — no reason to use long or short
    int customerAge = 35;

    // boolean for flags
    boolean isAccountFrozen = false;
    boolean hasOverdraftProtection = true;

    // char for account type code
    char accountType = 'S';  // S = Savings, C = Current
}
```

> **Lesson:** In finance, never use `double` for money. `0.1 + 0.2 != 0.3` in IEEE 754 floating point. Java's `BigDecimal` provides exact decimal arithmetic.

---

# Chapter 02 — Operators

```java
// Arithmetic
int a = 15, b = 4;
a + b;   // 19
a - b;   // 11
a * b;   // 60
a / b;   // 3       (integer division — truncates!)
a % b;   // 3       (remainder)

// Unary
int n = 5;
n++;     // post-increment: use n (5), THEN increment → n is now 6
++n;     // pre-increment:  increment FIRST, THEN use → n is now 7
n--;     // post-decrement

// Comparison (always produces boolean)
a == b;   a != b;   a > b;   a < b;   a >= b;   a <= b;

// Logical
true && false;  // AND → false   (short-circuits: stops if first is false)
true || false;  // OR  → true    (short-circuits: stops if first is true)
!true;          // NOT → false

// Bitwise (operates on individual bits)
5 & 3;    // 1    (AND: 0101 & 0011 = 0001)
5 | 3;    // 7    (OR:  0101 | 0011 = 0111)
5 ^ 3;    // 6    (XOR: 0101 ^ 0011 = 0110)
~5;       // -6   (bitwise NOT, flips all bits)
5 << 1;   // 10   (left shift = multiply by 2)
5 >> 1;   // 2    (right shift = divide by 2)

// Compound Assignment
int x = 10;
x += 5;   // x = 15
x -= 3;   // x = 12
x *= 2;   // x = 24

// Ternary
int age = 20;
String status = (age >= 18) ? "Adult" : "Minor";

// instanceof (type checking)
Object obj = "hello";
if (obj instanceof String s) {     // pattern matching instanceof (Java 16+)
    System.out.println(s.toUpperCase());  // s is already cast
}
```

---

### 🔬 Practice Program 2 — Simple ATM Logic

```java
public class ATMOperations {
    public static void main(String[] args) {
        double balance = 5000.00;
        double withdrawAmount = 1500.00;
        double depositAmount = 750.50;

        // Check using logical operators
        boolean hasSufficientFunds = balance >= withdrawAmount;
        boolean isValidWithdraw = withdrawAmount > 0 && withdrawAmount % 100 == 0;

        System.out.println("Balance: ₹" + balance);
        System.out.println("Withdraw: ₹" + withdrawAmount);
        System.out.println("Valid amount (multiple of 100): " + isValidWithdraw);
        System.out.println("Sufficient funds: " + hasSufficientFunds);

        if (hasSufficientFunds && isValidWithdraw) {
            balance -= withdrawAmount;
            System.out.printf("Withdrawal successful! New balance: ₹%.2f%n", balance);
        }

        balance += depositAmount;
        System.out.printf("After deposit: ₹%.2f%n", balance);

        // Bitwise trick: check if a number is even (last bit = 0 means even)
        int notes = 15;
        System.out.println(notes + " is even: " + ((notes & 1) == 0));
    }
}
```

---

# Chapter 03 — Conditions

```java
// ── if / else if / else ────────────────────────────────────────────
int score = 78;

if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");    // ← prints this
} else {
    System.out.println("F");
}

// ── switch statement (classic) ─────────────────────────────────────
int month = 4;
switch (month) {
    case 1: case 3: case 5: case 7:
    case 8: case 10: case 12:
        System.out.println("31 days");
        break;
    case 4: case 6: case 9: case 11:
        System.out.println("30 days");   // ← prints this
        break;
    case 2:
        System.out.println("28/29 days");
        break;
    default:
        System.out.println("Invalid month");
}

// ── switch expression (Java 14+) — returns a value, no fall-through ──
String dayType = switch (month) {
    case 1, 3, 5, 7, 8, 10, 12 -> "31-day month";
    case 4, 6, 9, 11            -> "30-day month";
    case 2                       -> "February";
    default                      -> throw new IllegalArgumentException("Invalid: " + month);
};

// ── switch with yield (multi-statement switch expression) ─────────
int day = 3;
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> {
        String d = "Wednesday";
        System.out.println("Middle of the week!");
        yield d;              // yield returns the value from a block
    }
    default -> "Other";
};
```

---

### 🔬 Practice Program 3 — Food Delivery Pricing

```java
public class DeliveryPricing {
    public static void main(String[] args) {
        double orderTotal = 350.0;
        String membershipLevel = "GOLD";
        boolean isRainyDay = true;
        double distanceKm = 8.5;

        // Delivery charge based on distance
        double deliveryCharge = switch ((int) distanceKm / 5) {
            case 0 -> 20.0;          // 0-4 km
            case 1 -> 40.0;          // 5-9 km
            default -> 60.0;          // 10+ km
        };

        // Membership discount
        double discountPercent = switch (membershipLevel) {
            case "GOLD"   -> 15.0;
            case "SILVER" -> 10.0;
            case "BASIC"  -> 5.0;
            default       -> 0.0;
        };

        // Surcharge for bad weather
        if (isRainyDay) {
            deliveryCharge += 10.0;
            System.out.println("Rain surcharge applied: +₹10");
        }

        // Free delivery for orders over ₹500
        if (orderTotal >= 500) deliveryCharge = 0.0;

        double discount = orderTotal * discountPercent / 100;
        double finalAmount = orderTotal - discount + deliveryCharge;

        System.out.printf("Order Total:   ₹%.2f%n", orderTotal);
        System.out.printf("Discount (%s): -₹%.2f%n", membershipLevel, discount);
        System.out.printf("Delivery:       ₹%.2f%n", deliveryCharge);
        System.out.printf("You Pay:        ₹%.2f%n", finalAmount);
    }
}
```

---

# Chapter 04 — Loops

```java
// ── for loop — when iteration count is known ───────────────────────
for (int i = 0; i < 5; i++) {
    System.out.print(i + " ");   // 0 1 2 3 4
}

// ── while loop — when condition drives the loop ────────────────────
int count = 0;
while (count < 3) {
    System.out.println(count);
    count++;
}

// ── do-while — runs at least once, checks AFTER ────────────────────
int tries = 0;
do {
    System.out.println("Attempt #" + (tries + 1));
    tries++;
} while (tries < 3);

// ── enhanced for (for-each) — cleanest way to iterate ─────────────
int[] numbers = {10, 20, 30, 40};
for (int n : numbers) {
    System.out.println(n);
}

// ── Nested loops — multiplication table ───────────────────────────
for (int row = 1; row <= 5; row++) {
    for (int col = 1; col <= 5; col++) {
        System.out.printf("%4d", row * col);
    }
    System.out.println();
}

// ── Loop control ───────────────────────────────────────────────────
outer:
for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
        if (j == 3) continue;       // skip j=3 in inner loop
        if (i == 3) continue outer; // skip entire i=3 iteration
        if (i == 4) break outer;    // exit BOTH loops
        System.out.print("[" + i + "," + j + "] ");
    }
}
```

---

### 🔬 Practice Program 4 — Number Pattern + Prime Checker

```java
public class LoopPractice {

    // Check if a number is prime
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {  // only check up to sqrt
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Pattern:   1
        //           2 2
        //          3 3 3
        System.out.println("=== Triangle Pattern ===");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        // Print primes from 2 to 50
        System.out.println("\n=== Primes up to 50 ===");
        for (int n = 2; n <= 50; n++) {
            if (isPrime(n)) System.out.print(n + " ");
        }

        // Fibonacci sequence with while
        System.out.println("\n\n=== Fibonacci (first 10) ===");
        int a = 0, b = 1, iteration = 0;
        while (iteration < 10) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
            iteration++;
        }
    }
}
```

---

# Chapter 05 — Methods

A **method** is a named, reusable block of code. In Java, **all code lives inside a class**, and **all executable code lives inside a method**.

```java
public class MethodDemo {

    // Signature: [modifiers] [returnType] methodName([params])
    public static int add(int a, int b) {
        return a + b;
    }

    // void — returns nothing (performs an action instead)
    public static void printLine(String message) {
        System.out.println(">>> " + message);
    }

    // Method overloading — same name, different parameter lists
    public static double multiply(int a, int b)       { return a * b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double multiply(int a, int b, int c){ return a * b * c; }

    // Varargs — variable number of arguments (must be last param)
    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    // Recursion — a method that calls itself
    public static long factorial(int n) {
        if (n <= 1) return 1;          // base case — ALWAYS required!
        return n * factorial(n - 1);   // recursive case
    }

    // Pass by value — Java ALWAYS passes by value
    // For primitives: the VALUE is copied. Changes don't affect the original.
    // For objects: the REFERENCE is copied. Mutation affects the original object.
    public static void tryToChange(int x) {
        x = 999;    // only changes the LOCAL copy
    }

    public static void main(String[] args) {
        System.out.println(add(3, 4));          // 7
        printLine("Hello");                       // >>> Hello
        System.out.println(multiply(2, 3));       // 6.0
        System.out.println(multiply(2.5, 3.5));   // 8.75
        System.out.println(sum(1, 2, 3, 4, 5));   // 15
        System.out.println(factorial(10));          // 3628800

        int value = 10;
        tryToChange(value);
        System.out.println(value);               // still 10 — not changed!
    }
}
```

---

### 🔬 Practice Program 5 — Calculator with All Operations

```java
public class Calculator {

    static double add(double a, double b)      { return a + b; }
    static double subtract(double a, double b) { return a - b; }
    static double multiply(double a, double b) { return a * b; }
    static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
    static double power(double base, int exp) {
        double result = 1;
        for (int i = 0; i < Math.abs(exp); i++) result *= base;
        return exp < 0 ? 1 / result : result;
    }
    static double average(double... values) {
        double total = 0;
        for (double v : values) total += v;
        return total / values.length;
    }

    public static void main(String[] args) {
        System.out.println("10 + 3  = " + add(10, 3));
        System.out.println("10 - 3  = " + subtract(10, 3));
        System.out.println("10 × 3  = " + multiply(10, 3));
        System.out.println("10 ÷ 3  = " + String.format("%.4f", divide(10, 3)));
        System.out.println("2^8     = " + power(2, 8));
        System.out.println("Average of 70, 85, 92, 66: " + average(70, 85, 92, 66));
    }
}
```

---

# Chapter 06 — Arrays

```java
// ── Declaration and Initialization ────────────────────────────────
int[] numbers = new int[5];               // [0, 0, 0, 0, 0] — default initialized
int[] filled  = {10, 20, 30, 40, 50};    // array literal
int[] explicit = new int[]{1, 2, 3};      // alternate literal

String[] names = {"Alice", "Bob", "Carol"};

// ── Access ─────────────────────────────────────────────────────────
System.out.println(filled[0]);    // 10 (0-indexed)
System.out.println(filled[4]);    // 50 (last element)
System.out.println(filled.length); // 5 (field, NOT a method)

// ── Iteration ──────────────────────────────────────────────────────
for (int i = 0; i < filled.length; i++) {
    System.out.println("Index " + i + " → " + filled[i]);
}
for (int n : filled) {      // enhanced for — cleaner when index isn't needed
    System.out.println(n);
}

// ── Sorting and Searching (java.util.Arrays) ───────────────────────
import java.util.Arrays;
int[] nums = {5, 2, 8, 1, 9, 3};
Arrays.sort(nums);                         // in-place sort → {1, 2, 3, 5, 8, 9}
int idx = Arrays.binarySearch(nums, 5);    // 3 (MUST be sorted first!)
int[] copy = Arrays.copyOf(nums, nums.length);
System.out.println(Arrays.toString(nums)); // [1, 2, 3, 5, 8, 9]

// ── 2D Arrays — matrix ────────────────────────────────────────────
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
System.out.println(matrix[1][2]);   // 6 (row 1, col 2)

for (int[] row : matrix) {
    for (int val : row) {
        System.out.printf("%3d", val);
    }
    System.out.println();
}

// ── Jagged arrays — rows of different lengths ─────────────────────
int[][] jagged = new int[3][];
jagged[0] = new int[]{1};
jagged[1] = new int[]{2, 3};
jagged[2] = new int[]{4, 5, 6};
```

---

### 🔬 Practice Program 6 — Student Marks Analyzer

```java
import java.util.Arrays;

public class MarksAnalyzer {
    public static void main(String[] args) {
        int[] marks = {78, 92, 55, 88, 71, 63, 96, 44, 80, 67};
        int n = marks.length;

        // Stats
        int sum = 0, min = marks[0], max = marks[0];
        for (int m : marks) {
            sum += m;
            if (m < min) min = m;
            if (m > max) max = m;
        }
        double avg = (double) sum / n;

        // Grade distribution
        int[] grades = new int[5];   // A, B, C, D, F
        for (int m : marks) {
            if      (m >= 90) grades[0]++;   // A
            else if (m >= 80) grades[1]++;   // B
            else if (m >= 70) grades[2]++;   // C
            else if (m >= 60) grades[3]++;   // D
            else              grades[4]++;   // F
        }

        System.out.println("=== Class Report ===");
        System.out.println("Students:   " + n);
        System.out.printf("Average:    %.1f%n", avg);
        System.out.println("Highest:    " + max);
        System.out.println("Lowest:     " + min);
        System.out.println("A: " + grades[0] + " | B: " + grades[1] +
                           " | C: " + grades[2] + " | D: " + grades[3] +
                           " | F: " + grades[4]);

        // Rank the marks
        int[] sorted = Arrays.copyOf(marks, n);
        Arrays.sort(sorted);
        System.out.println("Top 3: " + sorted[n-1] + ", " + sorted[n-2] + ", " + sorted[n-3]);
    }
}
```

---

# Chapter 07 — Strings

In Java, `String` is **immutable** — once created, the characters inside cannot be changed. Every "modification" creates a new object.

```java
// ── Creation ───────────────────────────────────────────────────────
String s1 = "Hello";                     // string literal (stored in string pool)
String s2 = new String("Hello");         // explicit object (NOT in pool)

// s1 == s2 is FALSE (different references)
// s1.equals(s2) is TRUE (same content) — ALWAYS use equals() for String comparison!

// ── Common Methods ─────────────────────────────────────────────────
String str = "  Hello, World!  ";

str.length();                   // 17 (includes spaces)
str.trim();                      // "Hello, World!" (strips leading/trailing whitespace)
str.strip();                     // same as trim but Unicode-aware (Java 11+)
str.toUpperCase();               // "  HELLO, WORLD!  "
str.toLowerCase();               // "  hello, world!  "
str.charAt(7);                   // 'W'
str.indexOf("World");            // 9
str.contains("Hello");           // true
str.startsWith("  He");         // true
str.endsWith("!  ");            // true
str.replace("World", "Java");    // "  Hello, Java!  "
str.replaceAll("\\s+", "");     // "Hello,World!" (regex-based)
str.substring(7, 12);           // "World" (start inclusive, end exclusive)
str.split(", ");                 // ["  Hello", "World!  "]

// ── Comparison ─────────────────────────────────────────────────────
"hello".equals("hello");           // true  — content comparison
"hello".equalsIgnoreCase("HELLO"); // true  — case-insensitive
"apple".compareTo("banana");       // negative (a < b alphabetically)

// ── String Formatting ──────────────────────────────────────────────
String name = "Alice"; int age = 30;
String f1 = String.format("Name: %s, Age: %d", name, age);
String f2 = "Name: %s, Age: %d".formatted(name, age);   // Java 15+
System.out.printf("Balance: %.2f%n", 1234.5);

// ── StringBuilder — mutable, fast for many concatenations ─────────
// String concatenation in a loop creates N new String objects — use StringBuilder!
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(", ");
sb.append("World");
sb.insert(5, " Beautiful");   // insert at index 5
sb.delete(5, 15);             // delete range
sb.reverse();
String result = sb.toString();

// ── Text Blocks (Java 15+) — multi-line strings ───────────────────
String json = """
              {
                  "name": "Alice",
                  "age": 30
              }
              """;
```

---

### 🔬 Practice Program 7 — Password Validator

```java
public class PasswordValidator {

    static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false, hasLower = false,
                hasDigit = false, hasSpecial = false;
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c))            hasUpper   = true;
            else if (Character.isLowerCase(c))       hasLower   = true;
            else if (Character.isDigit(c))           hasDigit   = true;
            else if (special.indexOf(c) >= 0)        hasSpecial = true;
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    static String maskPassword(String password) {
        if (password.length() <= 3) return "***";
        return password.substring(0, 2) +
               "*".repeat(password.length() - 3) +
               password.charAt(password.length() - 1);
    }

    public static void main(String[] args) {
        String[] passwords = {"abc", "password", "Pass@123", "secureP@ss1"};
        for (String pw : passwords) {
            System.out.printf("%-20s → Valid: %-5s Masked: %s%n",
                pw, isValidPassword(pw), maskPassword(pw));
        }
    }
}
```

**Output:**
```
abc                  → Valid: false Masked: ***
password             → Valid: false Masked: pa*****d
Pass@123             → Valid: true  Masked: Pa*****3
secureP@ss1          → Valid: true  Masked: se*******1
```

---

# Chapter 08 — Classes & Objects

A **class** is the blueprint. An **object** is the living, breathing instance of that blueprint in memory. Java is a class-centric language — everything meaningful lives inside a class.

```java
public class Car {
    // ── Fields (instance variables) ───────────────────────────────
    String brand;
    String model;
    int year;
    double price;
    boolean isRunning;    // defaults to false

    // ── Methods (behavior) ────────────────────────────────────────
    void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " started.");
        } else {
            System.out.println("Already running.");
        }
    }

    void stop() {
        isRunning = false;
        System.out.println(brand + " stopped.");
    }

    void displayInfo() {
        System.out.printf("%d %s %s — ₹%.0f%n", year, brand, model, price);
    }

    // ── toString — called when you print an object ─────────────────
    @Override
    public String toString() {
        return year + " " + brand + " " + model;
    }

    // ── equals — defines what "equal" means for this object ────────
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car other)) return false;
        return year == other.year
            && brand.equals(other.brand)
            && model.equals(other.model);
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        // Creating objects with new keyword
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.model = "Camry";
        car1.year  = 2022;
        car1.price = 2_500_000;

        Car car2 = new Car();
        car2.brand = "Honda";
        car2.model = "Civic";
        car2.year  = 2023;

        car1.start();           // Toyota Camry started.
        car1.displayInfo();     // 2022 Toyota Camry — ₹2500000
        System.out.println(car1);  // calls toString() → 2022 Toyota Camry

        // Reference vs object
        Car car3 = car1;        // car3 points to the SAME object as car1
        car3.brand = "BMW";     // changes are visible through car1 too!
        System.out.println(car1.brand);  // BMW
    }
}
```

---

### 🔬 Practice Program 8 — Library Book System

```java
public class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;
    int totalCopies;
    int availableCopies;

    void checkout() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("'" + title + "' checked out. " + availableCopies + " left.");
        } else {
            System.out.println("Sorry, '" + title + "' is not available.");
        }
    }

    void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            System.out.println("'" + title + "' returned. " + availableCopies + " available.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s — %d/%d copies",
            isbn, title, author, availableCopies, totalCopies);
    }
}

class Library {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.title = "Clean Code";
        b1.author = "Robert C. Martin";
        b1.isbn = "978-0132350884";
        b1.totalCopies = 3;
        b1.availableCopies = 3;

        System.out.println(b1);
        b1.checkout();
        b1.checkout();
        b1.checkout();
        b1.checkout();  // should show "not available"
        b1.returnBook();
        System.out.println(b1);
    }
}
```

---

# Chapter 09 — Constructors

A **constructor** is a special method that initializes an object when it's created with `new`. It has the same name as the class and no return type.

```java
public class Employee {
    String name;
    String department;
    double salary;
    int employeeId;
    static int nextId = 1000;   // shared counter across all employees

    // ── No-arg constructor (default behavior) ─────────────────────
    public Employee() {
        this.employeeId = nextId++;
        this.salary = 30_000;          // default salary
    }

    // ── Parameterized constructor ──────────────────────────────────
    public Employee(String name, String department) {
        this();                        // chains to no-arg constructor first!
        this.name = name;
        this.department = department;
    }

    // ── Full constructor ───────────────────────────────────────────
    public Employee(String name, String department, double salary) {
        this(name, department);        // chain to 2-param constructor
        this.salary = salary;
    }

    // ── Copy constructor ───────────────────────────────────────────
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public void promote(double raiseAmount) {
        this.salary += raiseAmount;
        System.out.printf("%s promoted! New salary: ₹%.0f%n", name, salary);
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', dept='%s', salary=₹%.0f}",
            employeeId, name, department, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee();                            // id=1000, salary=30000
        Employee e2 = new Employee("Alice", "Engineering");     // id=1001, salary=30000
        Employee e3 = new Employee("Bob", "Design", 85_000);    // id=1002, salary=85000
        Employee e4 = new Employee(e3);                          // copy of e3, new id=1003

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);

        e3.promote(15_000);
        System.out.println("After raise: " + e3.salary);
        System.out.println("Copy unchanged: " + e4.salary);   // copy isn't affected
    }
}
```

---

# Chapter 10 — Access Modifiers & Packages

Access modifiers control **who can see and use** a class member.

| Modifier      | Same Class | Same Package | Subclass | Everywhere |
|---------------|:----------:|:------------:|:--------:|:----------:|
| `public`      | ✅         | ✅           | ✅       | ✅         |
| `protected`   | ✅         | ✅           | ✅       | ❌         |
| *(default)*   | ✅         | ✅           | ❌       | ❌         |
| `private`     | ✅         | ❌           | ❌       | ❌         |

```java
// com/myapp/models/Product.java
package com.myapp.models;

public class Product {
    public String name;           // visible everywhere
    protected double costPrice;   // visible to package + subclasses
    double sellPrice;             // package-private (default)
    private int stockCount;       // only THIS class can touch it

    // Provide controlled access to private fields via getters/setters
    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int count) {
        if (count < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stockCount = count;
    }
}

// com/myapp/Main.java
package com.myapp;
import com.myapp.models.Product;

class Main {
    public static void main(String[] args) {
        Product p = new Product();
        p.name = "Laptop";          // ✅ public
        // p.stockCount = 10;       // ❌ compile error — private
        p.setStockCount(10);        // ✅ via public setter
        System.out.println(p.getStockCount());   // 10
    }
}
```

---

# Chapter 11 — Static & Final

## static

`static` members belong to the **class itself**, not to any instance. Every instance shares the same static field/method.

```java
public class MathUtils {
    // static final = constant (Java's equivalent of a constant)
    public static final double PI = 3.14159265358979;

    // static method — callable without creating an object: MathUtils.circle(5)
    public static double circleArea(double radius) {
        return PI * radius * radius;
    }

    public static double power(double base, int exp) {
        double result = 1;
        for (int i = 0; i < exp; i++) result *= base;
        return result;
    }

    // static field — shared counter across all instances
    private static int instanceCount = 0;

    public MathUtils() {
        instanceCount++;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }
}
```

## final

```java
// final variable — value cannot change after first assignment
final int MAX_RETRIES = 3;

// final method — cannot be overridden in a subclass
public final void criticalOperation() { ... }

// final class — cannot be subclassed (e.g., String, Integer are final)
public final class ImmutableConfig { ... }

// static initializer block — runs once when the class is loaded
public class Config {
    public static final String DATABASE_URL;

    static {
        // Complex initialization that can't fit in a field declaration
        DATABASE_URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:sqlite:default.db";
        System.out.println("Config loaded.");
    }
}
```

---

### 🔬 Practice Program 11 — Order ID Generator (static counter)

```java
public class Order {
    private static int orderCounter = 1000;
    private static int totalOrders = 0;
    private static double totalRevenue = 0;

    private final String orderId;
    private final String customerName;
    private final double amount;

    public Order(String customerName, double amount) {
        this.orderId = "ORD-" + (++orderCounter);
        this.customerName = customerName;
        this.amount = amount;
        totalOrders++;
        totalRevenue += amount;
    }

    public static void printStats() {
        System.out.printf("Total Orders: %d | Total Revenue: ₹%.2f%n",
            totalOrders, totalRevenue);
    }

    @Override
    public String toString() {
        return orderId + " | " + customerName + " | ₹" + amount;
    }

    public static void main(String[] args) {
        Order o1 = new Order("Alice",  1_200.00);
        Order o2 = new Order("Bob",    3_450.50);
        Order o3 = new Order("Carol",    850.00);

        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);
        Order.printStats();
    }
}
```

**Output:**
```
ORD-1001 | Alice  | ₹1200.0
ORD-1002 | Bob    | ₹3450.5
ORD-1003 | Carol  | ₹850.0
Total Orders: 3 | Total Revenue: ₹5500.50
```

---

# Chapter 12 — Inheritance

Inheritance lets a **child class** reuse, extend, and specialize the behavior of a **parent class**. Java supports only single class inheritance (one parent), but multiple interface inheritance.

```java
// ── Parent class ───────────────────────────────────────────────────
public class Vehicle {
    protected String brand;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
        this.fuelLevel = 100.0;
    }

    public void refuel(double amount) {
        fuelLevel = Math.min(100, fuelLevel + amount);
        System.out.println(brand + " refueled to " + fuelLevel + "%");
    }

    public String getInfo() {
        return year + " " + brand;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}

// ── Child class ────────────────────────────────────────────────────
public class Car extends Vehicle {
    private int numDoors;
    private String transmission;  // "manual" or "automatic"

    public Car(String brand, int year, int numDoors, String transmission) {
        super(brand, year);        // MUST call parent constructor first
        this.numDoors = numDoors;
        this.transmission = transmission;
    }

    @Override                      // @Override catches typos at compile time
    public String getInfo() {
        return super.getInfo() + " | " + numDoors + "-door " + transmission;
    }

    public void shiftGear(int gear) {
        if ("manual".equals(transmission)) {
            System.out.println("Shifting to gear " + gear);
        } else {
            System.out.println("Automatic transmission — no manual shifting.");
        }
    }
}

// ── Grandchild class ───────────────────────────────────────────────
public class ElectricCar extends Car {
    private int batteryCapacityKwh;

    public ElectricCar(String brand, int year, int battery) {
        super(brand, year, 4, "automatic");
        this.batteryCapacityKwh = battery;
    }

    @Override
    public void refuel(double amount) {
        // Override meaning: "charge" instead of "refuel"
        System.out.println(brand + " is charging... " + amount + " kWh added.");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | EV | " + batteryCapacityKwh + " kWh";
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 2022, 4, "automatic");
        ElectricCar tesla = new ElectricCar("Tesla", 2024, 100);

        System.out.println(myCar.getInfo());
        System.out.println(tesla.getInfo());

        myCar.refuel(30);
        tesla.refuel(50);    // calls the overridden version

        // instanceof checks inheritance chain
        System.out.println(tesla instanceof ElectricCar);  // true
        System.out.println(tesla instanceof Car);           // true!
        System.out.println(tesla instanceof Vehicle);       // true!
    }
}
```

---

# Chapter 13 — Polymorphism

**Polymorphism** means "many forms." A single interface or parent class reference can point to objects of different subclasses, and method calls resolve to the actual object's implementation at runtime.

```java
// ── Runtime Polymorphism (Dynamic Method Dispatch) ─────────────────
Vehicle[] fleet = {
    new Car("Toyota",  2022, 4, "automatic"),
    new Car("Honda",   2021, 2, "manual"),
    new ElectricCar("Tesla",  2024, 100),
    new ElectricCar("Rivian", 2023, 135),
};

for (Vehicle v : fleet) {
    // Java decides at RUNTIME which getInfo() to call based on the actual object
    System.out.println(v.getInfo());
    v.refuel(20);    // Car calls the Vehicle version, ElectricCar calls its override
}

// ── Compile-time Polymorphism (Method Overloading) ─────────────────
// Same method name, different parameter types/counts
public class Printer {
    void print(int n)     { System.out.println("Int: " + n); }
    void print(String s)  { System.out.println("Str: " + s); }
    void print(double d)  { System.out.println("Dbl: " + d); }
    void print(int a, int b) { System.out.println("Sum: " + (a + b)); }
}

// ── Casting ────────────────────────────────────────────────────────
Vehicle v = new Car("BMW", 2023, 4, "manual");

// Upcast — always safe (child → parent)
// Happens automatically in assignment above

// Downcast — may fail at runtime if the actual type doesn't match
if (v instanceof Car c) {          // pattern matching (Java 16+)
    c.shiftGear(3);                // can now access Car-specific methods
}

// Old way (pre Java 16):
// if (v instanceof Car) {
//     Car c = (Car) v;
//     c.shiftGear(3);
// }
```

---

### 🔬 Practice Program 13 — Payment System (Polymorphism showcase)

```java
abstract class Payment {
    protected double amount;
    Payment(double amount) { this.amount = amount; }

    abstract String processPayment();

    void displayReceipt() {
        System.out.println("─".repeat(30));
        System.out.printf("Amount: ₹%.2f%n", amount);
        System.out.println("Method: " + processPayment());
        System.out.println("─".repeat(30));
    }
}

class CreditCardPayment extends Payment {
    private String last4Digits;
    CreditCardPayment(double amount, String last4) {
        super(amount);
        this.last4Digits = last4;
    }
    @Override
    String processPayment() {
        return "Credit Card (****" + last4Digits + ")";
    }
}

class UpiPayment extends Payment {
    private String upiId;
    UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }
    @Override
    String processPayment() {
        return "UPI (" + upiId + ")";
    }
}

class CashPayment extends Payment {
    CashPayment(double amount) { super(amount); }
    @Override
    String processPayment() { return "Cash"; }
}

class PaymentProcessor {
    public static void main(String[] args) {
        Payment[] payments = {
            new CreditCardPayment(1_500.00, "4321"),
            new UpiPayment(850.00, "alice@upi"),
            new CashPayment(200.00),
        };
        for (Payment p : payments) {
            p.displayReceipt();   // correct processPayment() called for each
        }
    }
}
```

---

# Chapter 14 — Abstraction

Abstraction means hiding *how* something works, exposing only *what* it does. Java provides two tools:

1. **Abstract classes** — partially implemented blueprints (can have fields, constructors, concrete methods)
2. **Interfaces** — pure contracts (before Java 8: only abstract methods; since then: default + static methods too)

```java
// ── Abstract class ─────────────────────────────────────────────────
public abstract class DatabaseConnection {
    protected String url;
    protected String username;

    public DatabaseConnection(String url, String username) {
        this.url = url;
        this.username = username;
    }

    // Abstract — subclasses MUST implement; no body here
    public abstract void connect();
    public abstract void disconnect();
    public abstract boolean executeQuery(String sql);

    // Concrete — shared logic, no override needed
    public void testConnection() {
        System.out.println("Testing connection to " + url);
        connect();
        System.out.println("Connection test successful.");
        disconnect();
    }
}

public class MySQLConnection extends DatabaseConnection {
    public MySQLConnection(String url, String username) {
        super(url, username);
    }
    @Override public void connect()    { System.out.println("MySQL connected: " + url); }
    @Override public void disconnect() { System.out.println("MySQL disconnected."); }
    @Override public boolean executeQuery(String sql) {
        System.out.println("MySQL executing: " + sql);
        return true;
    }
}

// ── Interface ──────────────────────────────────────────────────────
public interface Printable {
    void print();                       // abstract (implicitly public abstract)

    default void printPreview() {       // default method (Java 8+) — has a body
        System.out.println("[Preview] ");
        print();
    }

    static void printAll(Printable[] items) {  // static helper on the interface
        for (Printable p : items) p.print();
    }
}

public interface Saveable {
    boolean save(String path);
}

// Implementing multiple interfaces (Java's answer to multiple inheritance)
public class Report implements Printable, Saveable {
    private String content;

    public Report(String content) { this.content = content; }

    @Override public void print()  { System.out.println(content); }
    @Override public boolean save(String path) {
        System.out.println("Saved to " + path);
        return true;
    }
}
```

---

# Chapter 15 — Encapsulation

Encapsulation means wrapping data (fields) and behavior (methods) together, hiding the internal state and only exposing what's necessary through a controlled public interface.

```java
public class BankAccount {
    // Private fields — the outside world cannot directly touch these
    private final String accountNumber;
    private final String owner;
    private double balance;
    private int failedAttempts;
    private boolean isLocked;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MAX_DAILY_WITHDRAW = 50_000;

    public BankAccount(String accountNumber, String owner, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
    }

    // ── Getters — controlled read access ───────────────────────────
    public String getAccountNumber() { return "****" + accountNumber.substring(accountNumber.length() - 4); }
    public String getOwner()         { return owner; }
    public double getBalance()       { return balance; }
    public boolean isLocked()        { return isLocked; }

    // ── No setter for balance — only controlled operations ─────────
    public void deposit(double amount) {
        if (isLocked) { System.out.println("Account is locked!"); return; }
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        System.out.printf("Deposited ₹%.2f. New balance: ₹%.2f%n", amount, balance);
    }

    public boolean withdraw(double amount) {
        if (isLocked) { System.out.println("Account is locked!"); return false; }
        if (amount <= 0 || amount > MAX_DAILY_WITHDRAW) return false;
        if (amount > balance) {
            failedAttempts++;
            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                isLocked = true;
                System.out.println("Account locked after too many failed attempts.");
            }
            return false;
        }
        balance -= amount;
        failedAttempts = 0;
        System.out.printf("Withdrew ₹%.2f. Balance: ₹%.2f%n", amount, balance);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Account[%s | Owner: %s | Balance: ₹%.2f | Locked: %s]",
            getAccountNumber(), owner, balance, isLocked);
    }
}
```

---

# Chapter 16 — Inner Classes

Java allows classes to be defined inside other classes. Each type serves a specific design purpose.

```java
public class OuterClass {

    private int outerValue = 100;

    // ── 1. Non-static inner class — tied to an instance of Outer ───
    class Inner {
        void display() {
            System.out.println("Outer value: " + outerValue);  // can access outer private!
        }
    }

    // ── 2. Static nested class — doesn't need an Outer instance ────
    static class StaticNested {
        void display() {
            System.out.println("I'm a static nested class — no Outer instance needed");
        }
    }

    // ── 3. Local class — defined inside a method ───────────────────
    void doSomething() {
        int localVal = 42;      // must be effectively final to use inside local class
        class LocalHelper {
            void run() {
                System.out.println("Local class sees: " + localVal + ", " + outerValue);
            }
        }
        new LocalHelper().run();
    }

    // ── 4. Anonymous class — one-off implementation with no name ───
    // Very common before lambdas; still useful for abstract classes
    Runnable task = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous Runnable running!");
        }
    };
}
```

```java
public class InnerClassDemo {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();

        // Non-static inner: needs outer instance
        OuterClass.Inner inner = outer.new Inner();
        inner.display();

        // Static nested: no outer instance needed
        OuterClass.StaticNested nested = new OuterClass.StaticNested();
        nested.display();

        outer.doSomething();
        outer.task.run();
    }
}
```

---

### 🔬 Practice Program 16 — GUI Button Simulation (Anonymous class pattern)

```java
interface ClickListener {
    void onClick(String buttonId);
}

class Button {
    private String id;
    private ClickListener listener;

    public Button(String id) { this.id = id; }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public void click() {
        if (listener != null) listener.onClick(id);
    }
}

public class AnonymousClassDemo {
    public static void main(String[] args) {
        Button saveBtn = new Button("save");
        Button deleteBtn = new Button("delete");

        // Anonymous class — unique logic per button, no need to name the class
        saveBtn.setClickListener(new ClickListener() {
            @Override
            public void onClick(String id) {
                System.out.println("[" + id.toUpperCase() + "] Saving data...");
            }
        });

        deleteBtn.setClickListener(new ClickListener() {
            @Override
            public void onClick(String id) {
                System.out.println("[" + id.toUpperCase() + "] Are you sure? Deleting...");
            }
        });

        saveBtn.click();     // [SAVE] Saving data...
        deleteBtn.click();   // [DELETE] Are you sure? Deleting...

        // Lambda equivalent (much cleaner — covered in Chapter 24)
        Button printBtn = new Button("print");
        printBtn.setClickListener(id -> System.out.println("[PRINT] Sending to printer..."));
        printBtn.click();
    }
}
```

---

### 🌍 Real-World Example — Builder Pattern using static nested class

```java
public class HttpRequest {
    private final String url;
    private final String method;
    private final String body;
    private final int timeoutSeconds;

    // Private constructor — only the Builder can call it
    private HttpRequest(Builder builder) {
        this.url            = builder.url;
        this.method         = builder.method;
        this.body           = builder.body;
        this.timeoutSeconds = builder.timeoutSeconds;
    }

    @Override
    public String toString() {
        return method + " " + url + " (timeout=" + timeoutSeconds + "s)";
    }

    // Static nested Builder
    public static class Builder {
        private final String url;
        private String method  = "GET";
        private String body    = "";
        private int timeoutSeconds = 30;

        public Builder(String url) { this.url = url; }
        public Builder method(String method)   { this.method  = method;  return this; }
        public Builder body(String body)       { this.body    = body;    return this; }
        public Builder timeout(int seconds)    { this.timeoutSeconds = seconds; return this; }
        public HttpRequest build()             { return new HttpRequest(this); }
    }

    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.Builder("https://api.example.com/users")
            .method("POST")
            .body("{\"name\":\"Alice\"}")
            .timeout(10)
            .build();
        System.out.println(request);
    }
}
```

---

# Chapter 17 — Enums

An **enum** (enumeration) is a special class that represents a fixed set of constants. Enums in Java are far more powerful than in most languages — they are full classes that can have fields, constructors, and methods.

```java
// ── Basic enum ─────────────────────────────────────────────────────
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

Day today = Day.MONDAY;
System.out.println(today);           // MONDAY
System.out.println(today.name());    // "MONDAY" (String name)
System.out.println(today.ordinal()); // 0 (position in declaration)

Day[] allDays = Day.values();        // array of all enum constants
Day friday = Day.valueOf("FRIDAY");  // String → enum constant

// switch with enum
String type = switch (today) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};
```

```java
// ── Enum with fields, constructor, and methods ─────────────────────
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS  (4.869e+24, 6.0518e6),
    EARTH  (5.976e+24, 6.37814e6),
    MARS   (6.421e+23, 3.3972e6);

    private final double mass;    // kg
    private final double radius;  // meters
    static final double G = 6.67300E-11;

    // Enum constructor is always private
    Planet(double mass, double radius) {
        this.mass   = mass;
        this.radius = radius;
    }

    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }

    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }

    public double getMassKg()  { return mass; }
    public double getRadiusM() { return radius; }
}
```

---

### 🔬 Practice Program 17 — Traffic Light System

```java
public enum TrafficLight {
    RED("Stop — do not cross!", 30),
    YELLOW("Prepare to stop.", 5),
    GREEN("Go ahead!", 25);

    private final String instruction;
    private final int durationSeconds;

    TrafficLight(String instruction, int duration) {
        this.instruction       = instruction;
        this.durationSeconds   = duration;
    }

    public TrafficLight next() {
        return switch (this) {
            case RED    -> GREEN;
            case GREEN  -> YELLOW;
            case YELLOW -> RED;
        };
    }

    public void display() {
        System.out.printf("🚦 %-8s | %-30s | %ds%n",
            name(), instruction, durationSeconds);
    }

    public static void main(String[] args) {
        TrafficLight light = TrafficLight.RED;

        System.out.println("=== Traffic Light Cycle ===");
        for (int i = 0; i < 6; i++) {
            light.display();
            light = light.next();
        }
    }
}
```

**Output:**
```
🚦 RED      | Stop — do not cross!          | 30s
🚦 GREEN    | Go ahead!                      | 25s
🚦 YELLOW   | Prepare to stop.               | 5s
🚦 RED      | Stop — do not cross!          | 30s
...
```

---

### 🌍 Real-World Example — HTTP Status Codes as Enum

```java
public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode()           { return code; }
    public String getDescription() { return description; }

    public boolean isSuccess()     { return code >= 200 && code < 300; }
    public boolean isError()       { return code >= 400; }

    public static HttpStatus fromCode(int code) {
        for (HttpStatus s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unknown HTTP status: " + code);
    }

    @Override
    public String toString() {
        return code + " " + description;
    }
}
```

---

# Chapter 18 — Generics

Generics let you write code that works with different types while keeping **full compile-time type safety**. Instead of writing a `StringBox`, `IntegerBox`, `DoubleBox`, you write one `Box<T>`.

```java
// ── Generic class ──────────────────────────────────────────────────
public class Box<T> {
    private T value;

    public Box(T value) { this.value = value; }
    public T getValue()  { return value; }
    public void setValue(T value) { this.value = value; }

    @Override
    public String toString() {
        return "Box[" + value + " (" + value.getClass().getSimpleName() + ")]";
    }
}

Box<String>  strBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(42);
Box<Double>  dblBox = new Box<>(3.14);

System.out.println(strBox);   // Box[Hello (String)]
System.out.println(intBox);   // Box[42 (Integer)]

// ── Generic method ─────────────────────────────────────────────────
public static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

// ── Bounded type parameters ────────────────────────────────────────
// T extends Number means T can be Integer, Double, Float, Long, etc.
public static <T extends Number> double sum(T[] arr) {
    double total = 0;
    for (T item : arr) total += item.doubleValue();
    return total;
}

Integer[] ints    = {1, 2, 3, 4, 5};
Double[]  doubles = {1.1, 2.2, 3.3};
System.out.println(sum(ints));     // 15.0
System.out.println(sum(doubles));  // 6.6

// ── Wildcards ──────────────────────────────────────────────────────
// ? — unknown type
// ? extends T — upper bound (T or any subtype)
// ? super T   — lower bound  (T or any supertype)

public static void printList(List<?> list) {  // any type of List
    for (Object item : list) System.out.print(item + " ");
}

public static double sumList(List<? extends Number> list) {  // Number or subtype
    double total = 0;
    for (Number n : list) total += n.doubleValue();
    return total;
}

// ── Generic interface ──────────────────────────────────────────────
public interface Comparable<T> {
    int compareTo(T other);
}
```

---

### 🔬 Practice Program 18 — Generic Stack & Generic Pair

```java
import java.util.ArrayList;

public class GenericStack<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void push(T item)          { items.add(item); }
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return items.remove(items.size() - 1);
    }
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return items.get(items.size() - 1);
    }
    public boolean isEmpty()          { return items.isEmpty(); }
    public int size()                  { return items.size(); }

    @Override
    public String toString()           { return items.toString(); }

    public static void main(String[] args) {
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(10); intStack.push(20); intStack.push(30);
        System.out.println("Stack: " + intStack);
        System.out.println("Pop:   " + intStack.pop());
        System.out.println("Peek:  " + intStack.peek());

        GenericStack<String> strStack = new GenericStack<>();
        strStack.push("Java"); strStack.push("Generics"); strStack.push("Rock");
        System.out.println("String Stack: " + strStack);

        // Generic Pair — two potentially different types
        record Pair<A, B>(A first, B second) {
            @Override
            public String toString() {
                return "(" + first + ", " + second + ")";
            }
        }

        Pair<String, Integer> student = new Pair<>("Alice", 95);
        Pair<String, String>  config  = new Pair<>("host", "localhost");
        System.out.println(student);   // (Alice, 95)
        System.out.println(config);    // (host, localhost)
    }
}
```

---

# Chapter 19 — Collections Framework

The **Java Collections Framework** (JCF) is a unified architecture of interfaces and classes for storing and manipulating groups of objects.

```
Collection (interface)
├── List — ordered, allows duplicates
│   ├── ArrayList   — fast random access, slow insert/remove in middle
│   ├── LinkedList  — fast insert/remove, slow random access
│   └── Vector      — thread-safe ArrayList (legacy)
├── Set — no duplicates
│   ├── HashSet     — fastest, no order
│   ├── LinkedHashSet — insertion order preserved
│   └── TreeSet     — sorted order
└── Queue / Deque
    ├── LinkedList
    ├── PriorityQueue — min-heap by default
    └── ArrayDeque  — double-ended queue

Map (separate hierarchy — not a Collection)
├── HashMap         — fast, no order
├── LinkedHashMap   — insertion order preserved
├── TreeMap         — sorted by key
└── Hashtable       — thread-safe (legacy)
```

## List

```java
import java.util.*;

// ArrayList — the go-to general purpose List
List<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");
fruits.add(1, "Blueberry");       // insert at index 1
fruits.remove("Banana");           // remove by object
fruits.remove(0);                  // remove by index
fruits.set(0, "Avocado");          // replace at index

System.out.println(fruits.get(0)); // Avocado
System.out.println(fruits.size());
System.out.println(fruits.contains("Cherry"));   // true
System.out.println(fruits.indexOf("Cherry"));    // index

// Sorting
Collections.sort(fruits);                              // natural (alphabetical)
fruits.sort(Comparator.reverseOrder());                // reverse
fruits.sort(Comparator.comparingInt(String::length));  // by length

// List.of() — immutable list (Java 9+)
List<String> immutable = List.of("A", "B", "C");
// immutable.add("D");  // ❌ UnsupportedOperationException

// List from array
String[] arr = {"X", "Y", "Z"};
List<String> fromArr = new ArrayList<>(Arrays.asList(arr));
```

## Set

```java
Set<String> hashSet = new HashSet<>();
hashSet.add("Alpha"); hashSet.add("Beta"); hashSet.add("Alpha"); // duplicate ignored
System.out.println(hashSet.size());   // 2

// LinkedHashSet — keeps insertion order
Set<String> linked = new LinkedHashSet<>(List.of("C", "A", "B"));
System.out.println(linked);   // [C, A, B]

// TreeSet — always sorted
Set<Integer> tree = new TreeSet<>(Set.of(5, 2, 8, 1));
System.out.println(tree);   // [1, 2, 5, 8]

// Set operations
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6));
a.retainAll(b);   // intersection → {3, 4}  (a is mutated)
a.addAll(b);      // union
a.removeAll(b);   // difference
```

## Map

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 92);
scores.put("Bob",   78);
scores.put("Carol", 88);
scores.put("Alice", 95);          // replaces 92

scores.get("Bob");                 // 78
scores.getOrDefault("Dave", 0);   // 0 — key doesn't exist
scores.containsKey("Alice");       // true
scores.remove("Bob");

// Iterate
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}
scores.forEach((name, score) -> System.out.println(name + ": " + score));

// Useful new methods (Java 8+)
scores.putIfAbsent("Eve", 80);     // only adds if key not present
scores.merge("Alice", 5, Integer::sum);  // Alice's score += 5
scores.computeIfAbsent("Frank", k -> 70); // compute value if key absent

// TreeMap — sorted by key
Map<String, Integer> sorted = new TreeMap<>(scores);

// Map.of() — immutable map (Java 9+)
Map<String, Integer> config = Map.of("timeout", 30, "retries", 3);
```

## Queue & Deque

```java
// Queue — FIFO
Queue<String> queue = new LinkedList<>();
queue.offer("First");     // add to tail (offer = safe add)
queue.offer("Second");
queue.offer("Third");
System.out.println(queue.poll());   // "First" — removes head
System.out.println(queue.peek());   // "Second" — reads head, no remove

// PriorityQueue — min-heap (smallest element always at head)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(30); pq.offer(10); pq.offer(20);
while (!pq.isEmpty()) System.out.print(pq.poll() + " ");  // 10 20 30

// ArrayDeque — stack or double-ended queue
Deque<String> deque = new ArrayDeque<>();
deque.push("A");   // push to front (stack behavior)
deque.push("B");
deque.offerLast("Z");  // add to back
System.out.println(deque.pop());       // "B" (from front)
System.out.println(deque.pollLast());  // "Z" (from back)
```

---

### 🔬 Practice Program 19 — Student Gradebook (Map + List)

```java
import java.util.*;

public class Gradebook {
    public static void main(String[] args) {
        // Student → list of their marks
        Map<String, List<Integer>> gradebook = new LinkedHashMap<>();
        gradebook.put("Alice",  List.of(88, 92, 79, 95, 86));
        gradebook.put("Bob",    List.of(72, 68, 75, 80, 70));
        gradebook.put("Carol",  List.of(95, 98, 92, 97, 100));
        gradebook.put("David",  List.of(55, 60, 48, 72, 65));

        System.out.printf("%-10s  %-8s  %-8s  %-8s  %-6s%n",
            "Student", "Average", "Highest", "Lowest", "Grade");
        System.out.println("-".repeat(50));

        Map<String, Double> averages = new LinkedHashMap<>();

        for (Map.Entry<String, List<Integer>> entry : gradebook.entrySet()) {
            String name = entry.getKey();
            List<Integer> marks = entry.getValue();

            double avg = marks.stream().mapToInt(i -> i).average().orElse(0);
            int max = Collections.max(marks);
            int min = Collections.min(marks);
            char grade = avg >= 90 ? 'A' : avg >= 75 ? 'B' : avg >= 60 ? 'C' : 'F';

            averages.put(name, avg);
            System.out.printf("%-10s  %-8.1f  %-8d  %-8d  %-6s%n",
                name, avg, max, min, grade);
        }

        // Find top student using Collections utility
        String topStudent = Collections.max(averages.entrySet(),
            Map.Entry.comparingByValue()).getKey();
        System.out.println("\n🏆 Top Student: " + topStudent);
    }
}
```

---

# Chapter 20 — Iterators & forEach

```java
import java.util.*;

List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Carol", "Dave"));

// ── for-each (enhanced for) ────────────────────────────────────────
for (String name : names) {
    System.out.println(name);
}

// ── Iterator — gives manual control, allows safe removal ──────────
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    String name = it.next();
    if (name.startsWith("C")) {
        it.remove();   // safe removal during iteration (NOT names.remove() in a loop!)
    }
}

// ── ListIterator — bidirectional, for Lists only ───────────────────
ListIterator<String> lit = names.listIterator(names.size()); // start from end
while (lit.hasPrevious()) {
    System.out.println(lit.previous());   // prints in reverse
}

// ── forEach with lambda (Java 8+) ─────────────────────────────────
names.forEach(name -> System.out.println(name.toUpperCase()));
names.forEach(System.out::println);  // method reference shorthand

// ── Map iteration patterns ─────────────────────────────────────────
Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);

map.forEach((k, v) -> System.out.println(k + "=" + v));

for (String key : map.keySet())   System.out.println(key);
for (Integer val : map.values())  System.out.println(val);
for (Map.Entry<String, Integer> e : map.entrySet())
    System.out.println(e.getKey() + ":" + e.getValue());
```

---

# Chapter 21 — Exception Handling

When something goes wrong at runtime, Java throws an **exception** — an object that represents the error. You either handle it or let it propagate.

```
Throwable
├── Error           — serious JVM problems (OutOfMemoryError, StackOverflowError)
│   └── Never catch these usually
└── Exception
    ├── RuntimeException     — UNCHECKED — your programming bugs
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ClassCastException
    │   ├── IllegalArgumentException
    │   └── ArithmeticException
    └── (other Exceptions)   — CHECKED — must handle or declare
        ├── IOException
        ├── SQLException
        └── FileNotFoundException
```

**Checked** exceptions must be handled (`try-catch`) or declared (`throws`). The compiler enforces this.
**Unchecked** exceptions (RuntimeExceptions) don't need to be declared — they usually indicate bugs.

```java
// ── try / catch / finally ─────────────────────────────────────────
public static int divide(int a, int b) {
    try {
        return a / b;
    } catch (ArithmeticException e) {
        System.out.println("Math error: " + e.getMessage());
        return -1;
    } finally {
        System.out.println("Finally always runs — great for cleanup.");
    }
}

// ── Multiple catch blocks ─────────────────────────────────────────
try {
    String s = null;
    int[] arr = {1, 2, 3};
    s.length();          // throws NullPointerException
    arr[10] = 5;         // throws ArrayIndexOutOfBoundsException
} catch (NullPointerException e) {
    System.out.println("Null reference: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Bad array index: " + e.getMessage());
} catch (Exception e) {           // catches anything not caught above
    System.out.println("General: " + e.getMessage());
}

// ── Multi-catch (Java 7+) — catch different exceptions the same way
try {
    riskyOperation();
} catch (IOException | SQLException e) {
    System.out.println("IO or DB error: " + e.getMessage());
}

// ── try-with-resources — auto-closes anything that implements AutoCloseable
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    String line;
    while ((line = br.readLine()) != null) System.out.println(line);
} catch (IOException e) {
    e.printStackTrace();
}
// fr and br are guaranteed closed, even if an exception occurred

// ── Throwing exceptions ────────────────────────────────────────────
public static void setAge(int age) {
    if (age < 0 || age > 150) {
        throw new IllegalArgumentException("Age out of range: " + age);
    }
}

// ── Checked exception — method must declare it with throws ─────────
public static String readFile(String path) throws IOException {
    return new String(java.nio.file.Files.readAllBytes(java.nio.file.Path.of(path)));
}

// ── Exception chaining — wrapping one exception in another ─────────
try {
    readFile("missing.txt");
} catch (IOException e) {
    throw new RuntimeException("Failed to load config", e);  // 'e' = cause
}
```

### Custom Exceptions

```java
// Checked custom exception
public class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(double shortfall) {
        super(String.format("Insufficient funds. Need ₹%.2f more.", shortfall));
        this.shortfall = shortfall;
    }

    public double getShortfall() { return shortfall; }
}

// Unchecked custom exception
public class ProductNotFoundException extends RuntimeException {
    private final String productId;

    public ProductNotFoundException(String productId) {
        super("Product not found: " + productId);
        this.productId = productId;
    }

    public String getProductId() { return productId; }
}
```

---

### 🔬 Practice Program 21 — Robust Bank Transfer

```java
public class RobustBank {

    static Map<String, Double> accounts = new HashMap<>(
        Map.of("ACC001", 5000.0, "ACC002", 2000.0, "ACC003", 8500.0)
    );

    static void transfer(String from, String to, double amount)
            throws InsufficientFundsException {

        if (!accounts.containsKey(from))
            throw new IllegalArgumentException("Source account not found: " + from);
        if (!accounts.containsKey(to))
            throw new IllegalArgumentException("Target account not found: " + to);
        if (amount <= 0)
            throw new IllegalArgumentException("Transfer amount must be positive");

        double fromBalance = accounts.get(from);
        if (fromBalance < amount)
            throw new InsufficientFundsException(amount - fromBalance);

        accounts.put(from, fromBalance - amount);
        accounts.put(to, accounts.get(to) + amount);
        System.out.printf("✅ Transferred ₹%.2f from %s to %s%n", amount, from, to);
    }

    public static void main(String[] args) {
        String[][] transactions = {
            {"ACC001", "ACC002", "1500"},
            {"ACC002", "ACC003", "5000"},   // will fail — insufficient funds
            {"ACC001", "ACC999", "100"},     // will fail — account not found
        };

        for (String[] t : transactions) {
            try {
                transfer(t[0], t[1], Double.parseDouble(t[2]));
            } catch (InsufficientFundsException e) {
                System.out.println("❌ Transfer failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid transfer: " + e.getMessage());
            }
        }

        System.out.println("\nFinal Balances:");
        accounts.forEach((id, bal) -> System.out.printf("  %s: ₹%.2f%n", id, bal));
    }
}
```

---

# Chapter 22 — File I/O

```java
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

// ── Modern NIO.2 approach (Java 7+) — prefer this ─────────────────

Path path = Path.of("notes.txt");

// Write
Files.writeString(path, "Hello, File World!\nSecond line.");
Files.write(path, List.of("Line 1", "Line 2", "Line 3")); // writes lines
Files.writeString(path, "appended\n", StandardOpenOption.APPEND);

// Read
String content  = Files.readString(path);
List<String> lines = Files.readAllLines(path);
byte[] bytes    = Files.readAllBytes(path);

// Stream lines — memory efficient for huge files
Files.lines(path).forEach(System.out::println);

// File operations
Files.exists(path);
Files.isDirectory(path);
Files.size(path);          // in bytes
Files.delete(path);
Files.copy(path, Path.of("backup.txt"), StandardCopyOption.REPLACE_EXISTING);
Files.move(path, Path.of("moved.txt"));

// Directories
Path dir = Path.of("myFolder");
Files.createDirectories(dir);
Files.list(dir).forEach(System.out::println);        // list immediate children
Files.walk(dir).forEach(System.out::println);         // walk recursively

// ── Classic IO (still useful for streams/sockets) ─────────────────

// Buffered reading line by line (large files)
try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// Buffered writing
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
    bw.write("New entry");
    bw.newLine();
}

// Object serialization — save/load Java objects to disk
// The class must implement Serializable
class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    String host = "localhost";
    int port = 8080;
}

// Save
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.ser"))) {
    oos.writeObject(new Config());
}

// Load
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("config.ser"))) {
    Config c = (Config) ois.readObject();
    System.out.println(c.host + ":" + c.port);
}
```

---

### 🔬 Practice Program 22 — Student CSV Reader/Writer

```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StudentCSV {

    record Student(String name, String subject, int marks) {
        String toCsv() { return name + "," + subject + "," + marks; }

        static Student fromCsv(String line) {
            String[] parts = line.split(",");
            return new Student(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()));
        }
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of("students.csv");

        // Write
        List<Student> students = List.of(
            new Student("Alice",  "Math",    92),
            new Student("Bob",    "Science", 78),
            new Student("Carol",  "Math",    88),
            new Student("David",  "Science", 65)
        );
        List<String> csvLines = new ArrayList<>();
        csvLines.add("Name,Subject,Marks");
        students.forEach(s -> csvLines.add(s.toCsv()));
        Files.write(file, csvLines);
        System.out.println("Written " + students.size() + " students.");

        // Read back
        List<String> raw = Files.readAllLines(file);
        raw.remove(0);  // skip header
        List<Student> loaded = raw.stream().map(Student::fromCsv).toList();

        System.out.println("\n=== CSV Data ===");
        loaded.forEach(s -> System.out.printf("%-10s | %-10s | %d%n",
            s.name(), s.subject(), s.marks()));

        // Average per subject
        Map<String, Double> avgBySubject = new HashMap<>();
        Map<String, Integer> countBySubject = new HashMap<>();
        for (Student s : loaded) {
            avgBySubject.merge(s.subject(), (double) s.marks(), Double::sum);
            countBySubject.merge(s.subject(), 1, Integer::sum);
        }
        System.out.println("\n=== Subject Averages ===");
        avgBySubject.forEach((subj, total) ->
            System.out.printf("%s: %.1f%n", subj, total / countBySubject.get(subj)));
    }
}
```

---

# Chapter 23 — Multithreading

Java has **built-in support for concurrency** through threads. A thread is an independent path of execution within a program.

```java
// ── Creating threads: 3 ways ───────────────────────────────────────

// 1. Extending Thread class
class CounterThread extends Thread {
    private String name;
    CounterThread(String name) { this.name = name; }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + ": " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}

// 2. Implementing Runnable (preferred — allows extending another class)
class PrintTask implements Runnable {
    private String message;
    PrintTask(String msg) { this.message = msg; }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + message);
        }
    }
}

// 3. Lambda (most concise, for simple tasks)
Thread t = new Thread(() -> System.out.println("Lambda thread!"));

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new CounterThread("Thread-A");
        Thread t2 = new CounterThread("Thread-B");

        t1.start();   // starts a new thread (DON'T call run() directly — that runs on main!)
        t2.start();

        t1.join();    // main thread waits for t1 to finish
        t2.join();    // main thread waits for t2 to finish
        System.out.println("Both threads finished.");
    }
}
```

### Synchronization — preventing race conditions

```java
public class Counter {
    private int count = 0;

    // synchronized — only one thread can execute this at a time
    public synchronized void increment() {
        count++;
    }

    // synchronized block — finer-grained locking
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() { return count; }
}
```

### ExecutorService — thread pool (production-grade)

```java
import java.util.concurrent.*;

// Fixed thread pool
ExecutorService executor = Executors.newFixedThreadPool(4);

for (int i = 0; i < 10; i++) {
    final int taskId = i;
    executor.submit(() -> {
        System.out.println("Task " + taskId + " on " + Thread.currentThread().getName());
    });
}

executor.shutdown();                 // no more tasks accepted
executor.awaitTermination(10, TimeUnit.SECONDS);  // wait for completion
```

### Future — get a result from an async task

```java
ExecutorService exec = Executors.newFixedThreadPool(2);

Future<Integer> future = exec.submit(() -> {
    Thread.sleep(1000);
    return 42;
});

System.out.println("Doing other work...");
int result = future.get();    // blocks until result is ready
System.out.println("Result: " + result);   // 42
exec.shutdown();
```

### CompletableFuture — modern async (Java 8+)

```java
CompletableFuture<String> cf = CompletableFuture
    .supplyAsync(() -> {
        // runs on ForkJoinPool
        return "Fetched data";
    })
    .thenApply(data -> data.toUpperCase())    // transform result
    .thenApply(data -> "Processed: " + data);

System.out.println(cf.get());   // Processed: FETCHED DATA

// Combining futures
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");

CompletableFuture<String> combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
System.out.println(combined.get());   // Hello World
```

---

### 🔬 Practice Program 23 — Parallel File Downloader Simulation

```java
import java.util.concurrent.*;
import java.util.*;

public class DownloadSimulator {

    record DownloadTask(String filename, long sizeMb) implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.printf("⬇ Starting: %-20s (%.0f MB)%n", filename, (double) sizeMb);
            Thread.sleep(sizeMb * 50);   // simulate download time
            System.out.printf("✅ Complete: %-20s%n", filename);
            return filename + " downloaded successfully";
        }
    }

    public static void main(String[] args) throws Exception {
        List<DownloadTask> tasks = List.of(
            new DownloadTask("ubuntu-22.iso",   800),
            new DownloadTask("jdk-21.tar.gz",   180),
            new DownloadTask("android-studio.dmg", 1000),
            new DownloadTask("intellij-idea.zip",  750)
        );

        ExecutorService pool = Executors.newFixedThreadPool(2);  // 2 concurrent downloads
        long start = System.currentTimeMillis();

        List<Future<String>> futures = pool.invokeAll(tasks);

        System.out.println("\n=== All Downloads Complete ===");
        for (Future<String> f : futures) {
            System.out.println("  " + f.get());
        }
        System.out.printf("Total time: %.1fs%n", (System.currentTimeMillis() - start) / 1000.0);
        pool.shutdown();
    }
}
```

---

# Chapter 24 — Lambdas & Functional Interfaces

A **lambda expression** is an anonymous function — a concise way to represent a single method interface implementation.

```java
// Before lambdas (anonymous class)
Comparator<String> comp = new Comparator<String>() {
    @Override
    public int compare(String a, String b) { return a.compareTo(b); }
};

// With lambda — much cleaner
Comparator<String> comp = (a, b) -> a.compareTo(b);

// Even shorter — method reference
Comparator<String> comp = String::compareTo;
```

### Functional Interfaces — the backbone of lambda expressions

A **functional interface** has exactly one abstract method (`@FunctionalInterface` enforces this).

```java
// ── Built-in functional interfaces (java.util.function) ───────────

// Predicate<T> — takes T, returns boolean
Predicate<String>  isLong    = s -> s.length() > 5;
Predicate<Integer> isEven    = n -> n % 2 == 0;
Predicate<Integer> isPositive = n -> n > 0;

isLong.test("Hello World");     // true
isEven.and(isPositive).test(4); // true (chaining predicates)
isEven.or(isPositive).test(-3); // false
isEven.negate().test(3);        // true

// Function<T, R> — takes T, returns R
Function<String, Integer>  strToLen  = String::length;
Function<Integer, String>  intToStr  = String::valueOf;
Function<String, String>   upperCase = String::toUpperCase;

strToLen.apply("Hello");           // 5
strToLen.andThen(intToStr).apply("Hello");  // "5" (chaining)

// BiFunction<T, U, R> — takes two inputs, returns R
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
add.apply(3, 4);   // 7

// Consumer<T> — takes T, returns void (performs side effect)
Consumer<String>  printer = System.out::println;
Consumer<String>  logger  = s -> System.err.println("[LOG] " + s);

printer.accept("Hello");
printer.andThen(logger).accept("Debug this");  // chaining consumers

// Supplier<T> — takes nothing, returns T
Supplier<Double>  random = Math::random;
Supplier<List<String>> listFactory = ArrayList::new;

random.get();          // random double
listFactory.get();     // new empty ArrayList

// UnaryOperator<T> — takes T, returns T (special Function)
UnaryOperator<String> trim   = String::trim;
UnaryOperator<String> upper  = String::toUpperCase;
UnaryOperator<String> clean  = trim.andThen(upper);

clean.apply("  hello  ");   // "HELLO"

// BinaryOperator<T> — takes two T, returns T
BinaryOperator<Integer> multiply = (a, b) -> a * b;
multiply.apply(3, 4);   // 12
```

### Method References

```java
// Four types of method references
names.forEach(System.out::println);             // 1. Static method ref:  ClassName::staticMethod
names.forEach(String::toUpperCase);             // 2. Instance method ref: ClassName::instanceMethod
names.forEach(myObj::myMethod);                  // 3. Instance on specific object: instance::method
List<String> strs = List.of("a","b","c");
strs.stream().map(String::new).toList();         // 4. Constructor ref:     ClassName::new
```

### Custom Functional Interface

```java
@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);

    // Default methods are allowed — they don't count as abstract
    default <V> Transformer<T, V> andThen(Transformer<R, V> after) {
        return input -> after.transform(this.transform(input));
    }
}

Transformer<String, Integer> length   = String::length;
Transformer<Integer, String> toStars  = n -> "*".repeat(n);
Transformer<String, String> combined  = length.andThen(toStars);

System.out.println(combined.transform("Hello"));   // *****
```

---

# Chapter 25 — Stream API

The **Stream API** lets you process collections in a declarative, pipeline style. A stream is a sequence of elements that supports sequential and parallel operations.

```
data source → intermediate ops (lazy) → terminal op (triggers execution)
```

```java
import java.util.stream.*;

List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// ── filter → map → collect ─────────────────────────────────────────
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)           // keep evens: 2,4,6,8,10
    .map(n -> n * n)                    // square them: 4,16,36,64,100
    .filter(n -> n > 20)               // keep > 20: 36,64,100
    .collect(Collectors.toList());      // terminal — triggers execution

// ── Common intermediate operations ────────────────────────────────
.filter(predicate)            // keep elements matching the predicate
.map(function)                 // transform each element
.flatMap(function)             // flatten nested collections into one stream
.distinct()                    // remove duplicates
.sorted()                      // natural sort
.sorted(comparator)            // custom sort
.limit(5)                      // take first 5
.skip(3)                       // skip first 3
.peek(consumer)                // debug: see elements passing through (doesn't consume)

// ── Common terminal operations ─────────────────────────────────────
.collect(Collectors.toList())      // → List
.collect(Collectors.toSet())       // → Set
.collect(Collectors.joining(", ")) // → String (for String streams)
.collect(Collectors.groupingBy(fn)) // → Map<K, List<V>>
.collect(Collectors.counting())    // → Long (count)
.toList()                          // → unmodifiable List (Java 16+)
.forEach(consumer)                 // side effect for each
.count()                           // number of elements
.sum() .average() .min() .max()    // (on IntStream / LongStream / DoubleStream)
.reduce(identity, accumulator)     // fold all elements into one value
.findFirst()                       // Optional<T> — first element
.findAny()                         // Optional<T> — any element (good for parallel)
.anyMatch(predicate)               // boolean: at least one matches
.allMatch(predicate)               // boolean: all match
.noneMatch(predicate)              // boolean: none match
```

### Working with Objects in Streams

```java
record Employee(String name, String dept, double salary) {}

List<Employee> employees = List.of(
    new Employee("Alice",  "Engineering", 90_000),
    new Employee("Bob",    "Marketing",   65_000),
    new Employee("Carol",  "Engineering", 105_000),
    new Employee("David",  "Marketing",   72_000),
    new Employee("Eve",    "Engineering", 98_000)
);

// Average salary by department
Map<String, Double> avgByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::dept,
        Collectors.averagingDouble(Employee::salary)
    ));

// Top earner per department
Map<String, Optional<Employee>> topEarner = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::dept,
        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
    ));

// All engineering names, sorted, joined
String engNames = employees.stream()
    .filter(e -> "Engineering".equals(e.dept()))
    .sorted(Comparator.comparing(Employee::name))
    .map(Employee::name)
    .collect(Collectors.joining(", "));
// "Alice, Carol, Eve"

// Total payroll
double totalPayroll = employees.stream()
    .mapToDouble(Employee::salary)
    .sum();

// Partition into high/low earners
Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.salary() > 80_000));
```

### Specialized Streams: IntStream, LongStream, DoubleStream

```java
// IntStream.range / rangeClosed
IntStream.range(1, 6).forEach(System.out::println);      // 1,2,3,4,5
IntStream.rangeClosed(1, 5).sum();                        // 15
IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count();  // 50

// Stream.generate and Stream.iterate (infinite streams — always limit!)
Stream.generate(Math::random).limit(5).forEach(System.out::println);
Stream.iterate(1, n -> n * 2).limit(10).forEach(System.out::println); // 1,2,4,8...
```

---

### 🔬 Practice Program 25 — E-Commerce Report

```java
import java.util.*;
import java.util.stream.*;

public class ECommerceReport {
    enum Category { ELECTRONICS, CLOTHING, BOOKS, FOOD }

    record Product(String name, Category category, double price, int sold) {
        double revenue() { return price * sold; }
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("iPhone 15",      Category.ELECTRONICS, 79_000, 120),
            new Product("Java Book",       Category.BOOKS,        1_200,  45),
            new Product("Nike Shoes",      Category.CLOTHING,    5_500,  200),
            new Product("Python Book",     Category.BOOKS,        980,    60),
            new Product("MacBook Pro",     Category.ELECTRONICS, 1_50_000, 35),
            new Product("Levi's Jeans",    Category.CLOTHING,    3_200,  310),
            new Product("Organic Rice 5kg",Category.FOOD,         450,   890)
        );

        // 1. Top 3 products by revenue
        System.out.println("=== Top 3 by Revenue ===");
        products.stream()
            .sorted(Comparator.comparingDouble(Product::revenue).reversed())
            .limit(3)
            .forEach(p -> System.out.printf("  %-20s ₹%,.0f%n", p.name(), p.revenue()));

        // 2. Revenue by category
        System.out.println("\n=== Revenue by Category ===");
        products.stream()
            .collect(Collectors.groupingBy(Product::category,
                     Collectors.summingDouble(Product::revenue)))
            .entrySet().stream()
            .sorted(Map.Entry.<Category, Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("  %-15s ₹%,.0f%n", e.getKey(), e.getValue()));

        // 3. Products under ₹1000, sorted by price
        System.out.println("\n=== Budget Products (under ₹1000) ===");
        products.stream()
            .filter(p -> p.price() < 1000)
            .sorted(Comparator.comparingDouble(Product::price))
            .map(p -> "  " + p.name() + " @ ₹" + p.price())
            .forEach(System.out::println);

        // 4. Total units sold
        int totalUnits = products.stream().mapToInt(Product::sold).sum();
        System.out.println("\nTotal units sold: " + totalUnits);

        // 5. Most popular product (by units sold)
        products.stream()
            .max(Comparator.comparingInt(Product::sold))
            .ifPresent(p -> System.out.println("Most popular: " + p.name()
                + " (" + p.sold() + " units)"));
    }
}
```

---

# Chapter 26 — Optional

`Optional<T>` is a container that may or may not hold a value. It eliminates `null` checks and `NullPointerException` by making the absence of a value explicit.

```java
import java.util.Optional;

// ── Creating Optional ──────────────────────────────────────────────
Optional<String> present = Optional.of("Hello");         // has a value
Optional<String> empty   = Optional.empty();             // no value
Optional<String> nullable = Optional.ofNullable(null);   // safe — empty if null

// ── Checking and getting ───────────────────────────────────────────
present.isPresent();           // true
empty.isPresent();             // false
present.isEmpty();             // false  (Java 11+)

present.get();                 // "Hello" (throws if empty — avoid naked get())

// ── Safe value retrieval ───────────────────────────────────────────
empty.orElse("default");              // "default" if empty
empty.orElseGet(() -> computeDefault()); // lazy — only called if empty
empty.orElseThrow(() -> new RuntimeException("Value required")); // throw if empty

// ── Transform with map / flatMap ───────────────────────────────────
Optional<String> name = Optional.of("  alice  ");
name.map(String::trim)                   // Optional["alice"]
    .map(String::toUpperCase)            // Optional["ALICE"]
    .ifPresent(System.out::println);     // prints "ALICE"

// flatMap — when your mapper also returns an Optional
Optional<String> email = findUser(1).flatMap(user -> getEmail(user.id));

// ── filter ─────────────────────────────────────────────────────────
Optional<Integer> score = Optional.of(85);
score.filter(s -> s >= 90)    // Optional.empty() because 85 < 90
     .orElse(0);              // 0

// ── Real-world pattern — chain without null checks ─────────────────
// Instead of:
// if (user != null && user.getAddress() != null && user.getAddress().getCity() != null) ...

// Use Optional:
String city = Optional.ofNullable(user)
    .map(User::getAddress)
    .map(Address::getCity)
    .orElse("Unknown");
```

---

### 🔬 Practice Program 26 — User Lookup Service

```java
import java.util.*;

public class UserLookup {
    record User(int id, String name, String email, String phone) {}

    static List<User> users = List.of(
        new User(1, "Alice",  "alice@mail.com",  "9900000001"),
        new User(2, "Bob",    "bob@mail.com",    null),
        new User(3, "Carol",  null,              "9900000003")
    );

    static Optional<User> findById(int id) {
        return users.stream().filter(u -> u.id() == id).findFirst();
    }

    static Optional<String> getEmail(int userId) {
        return findById(userId).map(User::email);
    }

    public static void main(String[] args) {
        int[] idsToCheck = {1, 2, 3, 99};

        for (int id : idsToCheck) {
            String name  = findById(id).map(User::name).orElse("Unknown User");
            String email = getEmail(id).orElse("No email on file");
            System.out.printf("ID %-3d → %-12s | %s%n", id, name, email);
        }

        // Collect only users WITH emails
        System.out.println("\nUsers with emails:");
        users.stream()
            .map(User::email)
            .filter(Objects::nonNull)
            .map(Optional::ofNullable)
            .flatMap(Optional::stream)   // Optional.stream() is Java 9+
            .forEach(e -> System.out.println("  " + e));
    }
}
```

---

# Chapter 27 — Records & Sealed Classes

## Records (Java 16+)

A **record** is a concise, immutable data carrier. The compiler auto-generates: constructor, getters, `equals()`, `hashCode()`, and `toString()`.

```java
// This single line replaces ~50 lines of boilerplate
public record Point(double x, double y) {}

// Equivalent to a class with:
//  - private final fields x, y
//  - canonical constructor Point(double x, double y)
//  - accessor methods x() and y() — NOT getX()/getY()
//  - equals(), hashCode(), toString()

Point p1 = new Point(3.0, 4.0);
Point p2 = new Point(3.0, 4.0);
System.out.println(p1);           // Point[x=3.0, y=4.0]
System.out.println(p1.equals(p2)); // true — value-based equality
System.out.println(p1.x());        // 3.0

// ── Custom compact constructor — for validation ────────────────────
public record Range(int min, int max) {
    Range {   // compact constructor — no () params, they're implicit
        if (min > max) throw new IllegalArgumentException("min must be <= max");
    }
}

// ── Records can have static fields, static methods, instance methods
public record Circle(double radius) {
    static final double PI = Math.PI;

    public double area()        { return PI * radius * radius; }
    public double circumference() { return 2 * PI * radius; }

    public static Circle unit() { return new Circle(1.0); }
}

// ── Records implementing interfaces ───────────────────────────────
public record Money(double amount, String currency) implements Comparable<Money> {
    @Override
    public int compareTo(Money other) {
        if (!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot compare different currencies");
        return Double.compare(this.amount, other.amount);
    }
}
```

## Sealed Classes (Java 17+)

A **sealed class** restricts which other classes can extend or implement it. Paired with records and pattern matching, it's Java's way to do algebraic data types.

```java
// Sealed class — only listed classes can extend it
public sealed class Shape permits Circle, Rectangle, Triangle {}

public record Circle(double radius)               extends Shape {}
public record Rectangle(double width, double height) extends Shape {}
public record Triangle(double base, double height)   extends Shape {}

// Pattern matching switch is exhaustive — compiler knows all subtypes
double area(Shape shape) {
    return switch (shape) {
        case Circle    c -> Math.PI * c.radius() * c.radius();
        case Rectangle r -> r.width() * r.height();
        case Triangle  t -> 0.5 * t.base() * t.height();
        // No default needed — compiler sees all cases are covered!
    };
}
```

---

### 🔬 Practice Program 27 — JSON-like AST with Sealed Classes

```java
public sealed interface JsonValue permits JsonString, JsonNumber, JsonBoolean, JsonNull, JsonArray {}

record JsonString(String value)   implements JsonValue {}
record JsonNumber(double value)   implements JsonValue {}
record JsonBoolean(boolean value) implements JsonValue {}
record JsonNull()                  implements JsonValue {}
record JsonArray(List<JsonValue> elements) implements JsonValue {}

public class JsonPrinter {

    static String stringify(JsonValue value) {
        return switch (value) {
            case JsonString  s -> "\"" + s.value() + "\"";
            case JsonNumber  n -> n.value() % 1 == 0
                ? String.valueOf((long) n.value())
                : String.valueOf(n.value());
            case JsonBoolean b -> String.valueOf(b.value());
            case JsonNull    ignored -> "null";
            case JsonArray   a -> "[" + a.elements().stream()
                .map(JsonPrinter::stringify)
                .collect(java.util.stream.Collectors.joining(", ")) + "]";
        };
    }

    public static void main(String[] args) {
        JsonValue json = new JsonArray(List.of(
            new JsonString("Alice"),
            new JsonNumber(30),
            new JsonBoolean(true),
            new JsonNull(),
            new JsonArray(List.of(new JsonNumber(1), new JsonNumber(2)))
        ));
        System.out.println(stringify(json));
        // ["Alice", 30, true, null, [1, 2]]
    }
}
```

---

# Chapter 28 — JDBC & Databases

**JDBC** (Java Database Connectivity) is Java's standard API for connecting to relational databases.

```java
import java.sql.*;

// ── JDBC connection ────────────────────────────────────────────────
String url  = "jdbc:sqlite:school.db";   // SQLite (built-in driver, no setup needed)
// For MySQL: "jdbc:mysql://localhost:3306/school"

try (Connection conn = DriverManager.getConnection(url)) {
    System.out.println("Connected to: " + conn.getMetaData().getDatabaseProductName());
}

// ── Create table ───────────────────────────────────────────────────
try (Connection conn = DriverManager.getConnection(url);
     Statement stmt  = conn.createStatement()) {

    stmt.execute("""
        CREATE TABLE IF NOT EXISTS students (
            id      INTEGER PRIMARY KEY AUTOINCREMENT,
            name    TEXT    NOT NULL,
            grade   TEXT,
            marks   INTEGER
        )
    """);
}

// ── INSERT with PreparedStatement (prevents SQL injection!) ────────
String insertSql = "INSERT INTO students (name, grade, marks) VALUES (?, ?, ?)";

try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement ps = conn.prepareStatement(insertSql)) {

    // Batch insert — efficient for many rows
    String[][] data = {{"Alice", "A", "92"}, {"Bob", "B", "78"}, {"Carol", "A", "88"}};
    for (String[] row : data) {
        ps.setString(1, row[0]);
        ps.setString(2, row[1]);
        ps.setInt(3, Integer.parseInt(row[2]));
        ps.addBatch();
    }
    int[] results = ps.executeBatch();
    System.out.println("Inserted " + results.length + " rows.");
}

// ── SELECT / ResultSet ─────────────────────────────────────────────
String selectSql = "SELECT * FROM students WHERE marks >= ? ORDER BY marks DESC";

try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement ps = conn.prepareStatement(selectSql)) {

    ps.setInt(1, 80);
    try (ResultSet rs = ps.executeQuery()) {
        System.out.printf("%-4s %-12s %-6s %-6s%n", "ID", "Name", "Grade", "Marks");
        while (rs.next()) {
            System.out.printf("%-4d %-12s %-6s %-6d%n",
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("grade"),
                rs.getInt("marks"));
        }
    }
}

// ── UPDATE ─────────────────────────────────────────────────────────
try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement ps = conn.prepareStatement(
         "UPDATE students SET grade = ? WHERE marks >= ?")) {
    ps.setString(1, "A+");
    ps.setInt(2, 90);
    int affected = ps.executeUpdate();
    System.out.println("Updated " + affected + " rows.");
}

// ── Transactions — all or nothing ─────────────────────────────────
try (Connection conn = DriverManager.getConnection(url)) {
    conn.setAutoCommit(false);   // start transaction
    try {
        // operation 1
        // operation 2
        conn.commit();            // both succeed — save
    } catch (SQLException e) {
        conn.rollback();          // one failed — undo ALL
        throw e;
    }
}
```

---

### 🔬 Practice Program 28 — Full CRUD Application

```java
import java.sql.*;
import java.util.*;

public class StudentDB {
    static final String URL = "jdbc:sqlite:students.db";

    static void createTable() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS students " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, subject TEXT, marks INTEGER)");
        }
    }

    static void insert(String name, String subject, int marks) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(
                 "INSERT INTO students (name, subject, marks) VALUES (?,?,?)")) {
            ps.setString(1, name); ps.setString(2, subject); ps.setInt(3, marks);
            ps.executeUpdate();
        }
    }

    static void update(int id, int newMarks) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(
                 "UPDATE students SET marks = ? WHERE id = ?")) {
            ps.setInt(1, newMarks); ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    static void delete(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement("DELETE FROM students WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    static void printAll() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL);
             ResultSet rs = c.createStatement().executeQuery(
                 "SELECT * FROM students ORDER BY marks DESC")) {
            System.out.printf("%-4s %-12s %-12s %-6s%n", "ID", "Name", "Subject", "Marks");
            System.out.println("-".repeat(38));
            while (rs.next()) {
                System.out.printf("%-4d %-12s %-12s %-6d%n",
                    rs.getInt("id"), rs.getString("name"),
                    rs.getString("subject"), rs.getInt("marks"));
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        createTable();
        insert("Alice",  "Math",    92);
        insert("Bob",    "Science", 78);
        insert("Carol",  "Math",    85);
        insert("David",  "Science", 91);
        System.out.println("=== All Students ==="); printAll();
        update(2, 88);
        System.out.println("\n=== After Updating Bob ==="); printAll();
        delete(4);
        System.out.println("\n=== After Deleting David ==="); printAll();
    }
}
```

---

# Chapter 29 — Design Patterns

Design patterns are proven, reusable solutions to recurring problems in software design.

---

## Creational Patterns

### Singleton — one instance, globally accessible

```java
public class AppConfig {
    private static volatile AppConfig instance;  // volatile for thread-safety

    private final String dbUrl;
    private final int maxConnections;

    private AppConfig() {
        dbUrl          = System.getenv().getOrDefault("DB_URL", "jdbc:sqlite:app.db");
        maxConnections = 10;
    }

    public static AppConfig getInstance() {
        if (instance == null) {                    // first check (no lock)
            synchronized (AppConfig.class) {
                if (instance == null) {            // second check (inside lock)
                    instance = new AppConfig();    // double-checked locking
                }
            }
        }
        return instance;
    }

    public String getDbUrl()       { return dbUrl; }
    public int getMaxConnections() { return maxConnections; }
}
```

### Factory Method — delegate object creation to subclasses

```java
interface Logger {
    void log(String message);
}
class ConsoleLogger  implements Logger { public void log(String m) { System.out.println("[CONSOLE] " + m); } }
class FileLogger     implements Logger { public void log(String m) { System.out.println("[FILE]    " + m); } }
class DatabaseLogger implements Logger { public void log(String m) { System.out.println("[DB]      " + m); } }

class LoggerFactory {
    public static Logger create(String type) {
        return switch (type.toLowerCase()) {
            case "console"  -> new ConsoleLogger();
            case "file"     -> new FileLogger();
            case "database" -> new DatabaseLogger();
            default -> throw new IllegalArgumentException("Unknown logger type: " + type);
        };
    }
}

// Usage — the caller doesn't need to know the concrete class
Logger logger = LoggerFactory.create("file");
logger.log("Application started");
```

### Builder — construct complex objects step by step

```java
public class Email {
    private final String from;
    private final List<String> to;
    private final String subject;
    private final String body;
    private final boolean isHtml;

    private Email(Builder b) {
        this.from    = b.from;
        this.to      = Collections.unmodifiableList(b.to);
        this.subject = b.subject;
        this.body    = b.body;
        this.isHtml  = b.isHtml;
    }

    public static class Builder {
        private String from;
        private List<String> to = new ArrayList<>();
        private String subject = "";
        private String body = "";
        private boolean isHtml = false;

        public Builder from(String from)       { this.from = from;       return this; }
        public Builder to(String... emails)    { this.to.addAll(List.of(emails)); return this; }
        public Builder subject(String subject) { this.subject = subject; return this; }
        public Builder body(String body)       { this.body = body;       return this; }
        public Builder html()                   { this.isHtml = true;    return this; }
        public Email build() {
            if (from == null) throw new IllegalStateException("Sender required");
            if (to.isEmpty()) throw new IllegalStateException("At least one recipient required");
            return new Email(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Email{from=%s, to=%s, subject='%s', html=%s}", from, to, subject, isHtml);
    }

    public static void main(String[] args) {
        Email email = new Email.Builder()
            .from("admin@app.com")
            .to("alice@mail.com", "bob@mail.com")
            .subject("Welcome to our platform!")
            .body("<h1>Hello!</h1><p>Thanks for joining.</p>")
            .html()
            .build();
        System.out.println(email);
    }
}
```

---

## Structural Patterns

### Repository — abstract data access

```java
interface ProductRepository {
    Optional<Product> findById(int id);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    void save(Product product);
    void delete(int id);
}

class InMemoryProductRepository implements ProductRepository {
    private Map<Integer, Product> store = new HashMap<>();
    private int nextId = 1;

    @Override
    public Optional<Product> findById(int id) { return Optional.ofNullable(store.get(id)); }

    @Override
    public List<Product> findAll() { return new ArrayList<>(store.values()); }

    @Override
    public List<Product> findByCategory(String category) {
        return store.values().stream()
            .filter(p -> category.equals(p.category()))
            .toList();
    }

    @Override
    public void save(Product product) { store.put(nextId++, product); }

    @Override
    public void delete(int id) { store.remove(id); }
}
```

### Decorator — wrap an object to add behavior

```java
interface TextProcessor { String process(String text); }

class PlainText implements TextProcessor {
    public String process(String text) { return text; }
}

abstract class TextDecorator implements TextProcessor {
    protected final TextProcessor wrapped;
    TextDecorator(TextProcessor wrapped) { this.wrapped = wrapped; }
}

class TrimDecorator extends TextDecorator {
    TrimDecorator(TextProcessor w) { super(w); }
    public String process(String text) { return wrapped.process(text).trim(); }
}

class UpperCaseDecorator extends TextDecorator {
    UpperCaseDecorator(TextProcessor w) { super(w); }
    public String process(String text) { return wrapped.process(text).toUpperCase(); }
}

class PrefixDecorator extends TextDecorator {
    private final String prefix;
    PrefixDecorator(TextProcessor w, String prefix) { super(w); this.prefix = prefix; }
    public String process(String text) { return prefix + wrapped.process(text); }
}

// Stacking decorators:
TextProcessor pipeline = new PrefixDecorator(
    new UpperCaseDecorator(
        new TrimDecorator(new PlainText())
    ), "[LOG] "
);
System.out.println(pipeline.process("  hello world  ")); // [LOG] HELLO WORLD
```

---

## Behavioral Patterns

### Observer — notify listeners when state changes

```java
import java.util.*;

interface EventListener<T> {
    void onEvent(T event);
}

class EventBus<T> {
    private final List<EventListener<T>> listeners = new ArrayList<>();

    public void subscribe(EventListener<T> listener)   { listeners.add(listener); }
    public void unsubscribe(EventListener<T> listener) { listeners.remove(listener); }

    public void publish(T event) {
        listeners.forEach(l -> l.onEvent(event));
    }
}

record OrderEvent(String type, int orderId, double amount) {}

// Usage
EventBus<OrderEvent> orderBus = new EventBus<>();

orderBus.subscribe(e -> System.out.printf("📧 Email: Order #%d %s%n", e.orderId(), e.type()));
orderBus.subscribe(e -> System.out.printf("📊 Analytics: ₹%.2f %s%n", e.amount(), e.type()));
orderBus.subscribe(e -> { if ("CANCELLED".equals(e.type()))
    System.out.println("⚠️  Alert: Order #" + e.orderId() + " cancelled!"); });

orderBus.publish(new OrderEvent("PLACED",    1001, 2_500.00));
orderBus.publish(new OrderEvent("SHIPPED",   1001, 2_500.00));
orderBus.publish(new OrderEvent("CANCELLED", 1002, 890.00));
```

### Strategy — swap algorithms at runtime

```java
@FunctionalInterface
interface SortStrategy<T extends Comparable<T>> {
    void sort(List<T> list);
}

class Sorter<T extends Comparable<T>> {
    private SortStrategy<T> strategy;

    public Sorter(SortStrategy<T> strategy) { this.strategy = strategy; }
    public void setStrategy(SortStrategy<T> s) { this.strategy = s; }
    public void sort(List<T> data) { strategy.sort(data); }
}

// Different strategies (could also use method references to Collections methods)
SortStrategy<Integer> ascending  = list -> Collections.sort(list);
SortStrategy<Integer> descending = list -> list.sort(Comparator.reverseOrder());
SortStrategy<Integer> random     = list -> Collections.shuffle(list);

List<Integer> nums = new ArrayList<>(List.of(5, 2, 8, 1, 9));
Sorter<Integer> sorter = new Sorter<>(ascending);
sorter.sort(nums);   System.out.println("Asc: " + nums);
sorter.setStrategy(descending);
sorter.sort(nums);   System.out.println("Desc: " + nums);
```

---

### 🔬 Practice Program 29 — Mini E-Commerce Engine

```java
import java.util.*;
import java.util.stream.*;

// ── Domain ─────────────────────────────────────────────────────────
record Product(String id, String name, double price) {}

// ── Strategy: discount calculation ───────────────────────────────
interface DiscountStrategy {
    double apply(double total);
    String description();
}

record PercentDiscount(double percent) implements DiscountStrategy {
    public double apply(double total) { return total * (1 - percent / 100); }
    public String description()       { return percent + "% off"; }
}

record FlatDiscount(double amount) implements DiscountStrategy {
    public double apply(double total) { return Math.max(0, total - amount); }
    public String description()       { return "Flat ₹" + amount + " off"; }
}

// ── Observer: order events ─────────────────────────────────────────
record OrderPlacedEvent(String orderId, double amount, List<Product> items) {}

// ── Shopping Cart (encapsulated) ───────────────────────────────────
class ShoppingCart {
    private final List<Product> items = new ArrayList<>();
    private DiscountStrategy discount = total -> total; // identity — no discount

    { discount = new DiscountStrategy() {
        public double apply(double t) { return t; }
        public String description()   { return "No discount"; }
    }; }

    public void add(Product p)                     { items.add(p); }
    public void setDiscount(DiscountStrategy d)    { this.discount = d; }

    public double subtotal() {
        return items.stream().mapToDouble(Product::price).sum();
    }

    public void printReceipt(String orderId) {
        double sub   = subtotal();
        double total = discount.apply(sub);
        System.out.println("=== Receipt: " + orderId + " ===");
        items.forEach(p -> System.out.printf("  %-20s ₹%.2f%n", p.name(), p.price()));
        System.out.println("  " + "-".repeat(28));
        System.out.printf("  Subtotal:             ₹%.2f%n", sub);
        System.out.printf("  Discount (%s): -₹%.2f%n", discount.description(), sub - total);
        System.out.printf("  Total:                ₹%.2f%n", total);
    }
}

public class ECommerceEngine {
    public static void main(String[] args) {
        List<Product> catalog = List.of(
            new Product("P1", "Mechanical Keyboard", 4_500),
            new Product("P2", "Wireless Mouse",      1_800),
            new Product("P3", "USB-C Hub",           2_200)
        );

        ShoppingCart cart = new ShoppingCart();
        cart.add(catalog.get(0));
        cart.add(catalog.get(1));
        cart.add(catalog.get(2));

        // Observer to track orders
        EventBus<OrderPlacedEvent> bus = new EventBus<>();
        bus.subscribe(e -> System.out.printf("%n✅ Order %s confirmed — ₹%.2f%n", e.orderId(), e.amount()));

        cart.setDiscount(new PercentDiscount(15));
        cart.printReceipt("ORD-2024-001");
        bus.publish(new OrderPlacedEvent("ORD-2024-001", cart.subtotal(), catalog));

        System.out.println();
        cart.setDiscount(new FlatDiscount(1000));
        cart.printReceipt("ORD-2024-002");
    }
}
```

---

# Chapter 30 — JVM Internals & Memory

Understanding how the JVM manages memory helps you write faster code and debug tricky bugs.

```
JVM Memory Layout
├── Heap (shared — all threads)
│   ├── Young Generation
│   │   ├── Eden Space         ← new objects born here
│   │   ├── Survivor S0
│   │   └── Survivor S1
│   └── Old (Tenured) Generation ← long-lived objects promoted here
├── Non-Heap
│   ├── Metaspace (Java 8+)    ← class metadata, method bytecode
│   └── Code Cache             ← JIT-compiled native code
└── Per-thread (private)
    ├── Stack                  ← stack frames (local vars, method calls)
    ├── PC Register            ← current bytecode instruction pointer
    └── Native Method Stack
```

### Garbage Collection

```java
// Java manages memory automatically via GC — you don't call free()
// GC runs when heap fills up (Young Gen first → Minor GC; Old Gen → Major/Full GC)

// You can suggest GC (but JVM decides when to actually run it)
System.gc();  // hint only — not a command

// Objects become eligible for GC when no live references point to them
String s = new String("Hello");
s = null;   // old "Hello" object is now eligible for collection

// Weak reference — doesn't prevent GC
import java.lang.ref.*;
WeakReference<String> weak = new WeakReference<>(new String("Temp"));
String value = weak.get();   // null if GC already collected it

// finalize() is deprecated in Java 9+ — use try-with-resources or Cleaner instead
```

### Stack vs Heap

```java
public class MemoryDemo {
    public static void main(String[] args) {
        // Primitives and references live on the STACK (per-method frame)
        int x = 10;            // x (value 10) → stack
        String s = "hello";    // s (reference) → stack, "hello" object → heap (string pool)

        // Objects always live on the HEAP
        Person p = new Person("Alice");  // p (reference) → stack, Person object → heap
    }   // when main() returns, its stack frame is popped; heap objects await GC
}
```

### JVM Tuning Flags

```bash
# Set initial and maximum heap size
java -Xms512m -Xmx2g MyApp

# Print GC activity
java -verbose:gc MyApp

# Use G1 GC (default since Java 9 — low-pause, good for large heaps)
java -XX:+UseG1GC MyApp

# Use ZGC (ultra-low-latency, Java 15+)
java -XX:+UseZGC MyApp

# See all JVM flags
java -XX:+PrintFlagsFinal -version
```

### Common Memory Issues

```java
// ── Memory Leak — holding references to objects you no longer need ─
// BAD: List grows forever, old events are never released
List<String> eventLog = new ArrayList<>();
while (running) {
    eventLog.add("event");   // list never trimmed → eventually OutOfMemoryError
}

// FIX: Use a bounded structure
int MAX_LOG_SIZE = 1000;
if (eventLog.size() >= MAX_LOG_SIZE) eventLog.remove(0);  // or use a CircularBuffer

// ── StackOverflowError — too deep recursion ───────────────────────
// Happens when the call stack runs out of frames (no base case, or too deep)
static int badFactorial(int n) {
    return n * badFactorial(n - 1);  // ❌ no base case — infinite recursion
}

// ── NullPointerException — the most common Java bug ───────────────
String name = null;
name.length();   // ❌ NullPointerException

// FIX: use Optional, null checks, or Objects.requireNonNull
Objects.requireNonNull(name, "name must not be null");
```

### Java Version Quick Reference

| Version | Key Feature |
|---------|------------|
| Java 8  | Lambdas, Stream API, Optional, default interface methods |
| Java 9  | Modules (JPMS), jshell REPL |
| Java 10 | `var` local type inference |
| Java 11 | `String.strip()`, `Files.readString()`, HTTP Client API (LTS) |
| Java 14 | Switch expressions (stable), helpful NPE messages |
| Java 15 | Text blocks (stable) |
| Java 16 | Records (stable), pattern matching instanceof |
| Java 17 | Sealed classes (stable) (LTS) |
| Java 21 | Virtual threads (Project Loom), sequenced collections, record patterns (LTS) |

### Virtual Threads (Java 21 — Project Loom)

```java
import java.util.concurrent.*;

// Traditional platform thread — heavy OS thread (~1MB stack)
Thread platform = new Thread(() -> System.out.println("Platform thread"));

// Virtual thread — ultra-lightweight (~few KB, millions possible)
Thread virtual = Thread.ofVirtual().start(() -> System.out.println("Virtual thread!"));

// ExecutorService with virtual threads — perfect for I/O-heavy tasks
try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 100_000; i++) {
        final int taskId = i;
        exec.submit(() -> {
            Thread.sleep(Duration.ofMillis(100));  // simulated I/O wait
            System.out.println("Task " + taskId + " done");
            return null;
        });
    }
}   // auto-close waits for all tasks
// Running 100k tasks with platform threads: minutes
// Running 100k tasks with virtual threads: seconds
```

---

## Quick Reference Card

| Concept              | Key Syntax / Class                                          |
|----------------------|--------------------------------------------------------------|
| Variable             | `int x = 5;`, `final`, `var`                                |
| Data types           | `int`, `long`, `double`, `boolean`, `char`, `String`        |
| OOP                  | `class`, `extends`, `implements`, `abstract`, `interface`   |
| Modifiers            | `public`, `private`, `protected`, `static`, `final`         |
| Collections          | `ArrayList`, `HashMap`, `HashSet`, `TreeMap`, `PriorityQueue` |
| Generics             | `<T>`, `<T extends X>`, `<?>`, `<? extends T>`             |
| Exceptions           | `try`, `catch`, `finally`, `throw`, `throws`                |
| Functional           | `Predicate`, `Function`, `Consumer`, `Supplier`, `lambda`   |
| Streams              | `.stream()`, `.filter()`, `.map()`, `.collect()`            |
| Optional             | `Optional.of()`, `.orElse()`, `.map()`, `.ifPresent()`      |
| Modern Java          | `record`, `sealed`, `switch` expression, `instanceof` pattern |
| Concurrency          | `Thread`, `ExecutorService`, `CompletableFuture`, virtual threads |
| File I/O             | `Files.readString()`, `Files.write()`, `Path`, `BufferedReader` |
| Database             | `Connection`, `PreparedStatement`, `ResultSet`, `JDBC`      |
| Design Patterns      | Singleton, Factory, Builder, Repository, Observer, Strategy |

---

*Write Once, Run Anywhere. Master Java — master the craft. ☕*

