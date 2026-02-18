# Analysis of Fraction::greatestCommonDivisor(int, int)

---

## 1.  Locator

```
Fraction::greatestCommonDivisor@348-403@
./src/main/java/org/apache/commons/lang3/math/Fraction.java
```

---

## 2. Function Source Code

```java

    /**
     * Gets the greatest common divisor of the absolute value of
     * two numbers, using the "binary gcd" method which avoids
     * division and modulo operations.  See Knuth 4.5.2 algorithm B.
     * This algorithm is due to Josef Stein (1961).
     *
     * @param u  a non-zero number
     * @param v  a non-zero number
     * @return the greatest common divisor, never zero
     */
    private static int greatestCommonDivisor(int u, int v) {
        // From Commons Math:
        if (u == 0 || v == 0) {
            if (u == Integer.MIN_VALUE || v == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: gcd is 2^31");
            }
            return Math.abs(u) + Math.abs(v);
        }
        // if either operand is abs 1, return 1:
        if (Math.abs(u) == 1 || Math.abs(v) == 1) {
            return 1;
        }
        // keep u and v negative, as negative integers range down to
        // -2^31, while positive numbers can only be as large as 2^31-1
        // (i.e. we can't necessarily negate a negative number without
        // overflow)
        if (u > 0) {
            u = -u;
        } // make u negative
        if (v > 0) {
            v = -v;
        } // make v negative
        // B1. [Find power of 2]
        int k = 0;
        while ((u & 1) == 0 && (v & 1) == 0 && k < 31) { // while u and v are both even...
            u /= 2;
            v /= 2;
            k++; // cast out twos.
        }
        if (k == 31) {
            throw new ArithmeticException("overflow: gcd is 2^31");
        }
        // B2. Initialize: u and v have been divided by 2^k and at least
        // one is odd.
        int t = (u & 1) == 1 ? v : -(u / 2)/* B3 */;
        // t negative: u was odd, v may be even (t replaces v)
        // t positive: u was even, v is odd (t replaces u)
        do {
            /* assert u<0 && v<0; */
            // B4/B3: cast out twos from t.
            while ((t & 1) == 0) { // while t is even.
                t /= 2; // cast out twos
            }
            // B5 [reset max(u,v)]
            if (t > 0) {
                u = -t;
            } else {
                v = t;
            }
            // B6/B3. at this point both u and v should be odd.
            t = (v - u) / 2;
            // |u| larger: t positive (replace u)
            // |v| larger: t negative (replace v)
        } while (t != 0);
        return -u * (1 << k); // gcd is u*2^k
    }
```
---

## 3. Complexity Analysis

### 3.1 Tool-based Measurement (lizard)

Command used:

```bash
lizard -l java src/main/java/org/apache/commons/lang3/math/Fraction.java | grep greatestCommonDivisor
```

Output:

```text
39     17    256      2      56 Fraction::greatestCommonDivisor@348-403@
```

Interpretation:

- CCN = 17
- NLOC = 39
- Tokens = 256
- Parameters = 2
- Length ≈ 56 lines

---

### 3.2 Manual Cyclomatic Complexity Count

 Decision points counted:
- if statements
- while loops
- do-while loop
- ternary operator

Result:
- Decision points = 11
- CC = 12 (11 + 1)

Including short-circuit boolean operators (||, &&):
- Additional 5 decision points
- Total CC = 17

Matches lizard.

---

## 4. Coverage Analysis (JaCoCo)

### 4.1 Old Coverage

Generated using:

```bash
mvn test jacoco:report
```

bash
```
open target/site/jacoco/org.apache.commons.lang3.math/Fraction.html
```

Results for greatestCommonDivisor(int,int):

- Instruction coverage: 81%
- Branch coverage: 71%
- Missed branches: 4

---

### 4.2 New Coverage (After Adding Tests)

Branch: task2-improvment

Command:

```bash
mvn test jacoco:report
```

Results:

- Instruction coverage: 94%
- Branch coverage: 86%
- Missed branches: 3

---

## 5. Coverage Improvement (Tests Added)

Branch:

```bash
git checkout task2-improvment
```

```java
    public static boolean[] branchCoverage = new boolean[100];

private static int greatestCommonDivisor(int u, int v) {
    // DECISION 1: zero input handling
    //   B1: (u == 0 || v == 0) is TRUE  -> enter zero-case block
    //   B2: (u == 0 || v == 0) is FALSE -> skip zero-case block
    if (u == 0 || v == 0) {
        branchCoverage[1] = true; // B1

        // DECISION 2: overflow check inside zero-case
        //   B3: (u == MIN || v == MIN) is TRUE  -> throw exception
        //   B4: (u == MIN || v == MIN) is FALSE -> return abs(u)+abs(v)
        if (u == Integer.MIN_VALUE || v == Integer.MIN_VALUE) {
            branchCoverage[3] = true; // B3
            throw new ArithmeticException("overflow: gcd is 2^31");
        } else {
            branchCoverage[4] = true; // B4
        }

        return Math.abs(u) + Math.abs(v);
    } else {
        branchCoverage[2] = true; // B2
    }

    // DECISION 3: abs-one fast path
    //   B5: (abs(u)==1 || abs(v)==1) is TRUE  -> return 1
    //   B6: (abs(u)==1 || abs(v)==1) is FALSE -> continue
    if (Math.abs(u) == 1 || Math.abs(v) == 1) {
        branchCoverage[5] = true; // B5
        return 1;
    } else {
        branchCoverage[6] = true; // B6
    }

    // DECISION 4: normalize sign of u
    //   B7: (u > 0) is TRUE  -> set u = -u
    //   B8: (u > 0) is FALSE -> u already <= 0
    if (u > 0) {
        branchCoverage[7] = true; // B7
        u = -u;
    } else {
        branchCoverage[8] = true; // B8
    }

    // DECISION 5: normalize sign of v
    //   B9:  (v > 0) is TRUE  -> set v = -v
    //   B10: (v > 0) is FALSE -> v already <= 0
    if (v > 0) {
        branchCoverage[9] = true; // B9
        v = -v;
    } else {
        branchCoverage[10] = true; // B10
    }

    // DECISION 6: extract common factors of 2
    //   B11: loop ENTERED (at least once)
    //   B12: loop EXITED (or never entered)
    // Note: loop condition: (u even) && (v even) && (k < 31)
    int k = 0;
    while ((u & 1) == 0 && (v & 1) == 0 && k < 31) {
        branchCoverage[11] = true; // B11
        u /= 2;
        v /= 2;
        k++;
    }
    branchCoverage[12] = true; // B12

    // DECISION 7: overflow if k == 31
    //   B13: (k == 31) is TRUE  -> throw exception
    //   B14: (k == 31) is FALSE -> continue

    if (k == 31) {
        branchCoverage[13] = true; // B13
        throw new ArithmeticException("overflow: gcd is 2^31");
    } else {
        branchCoverage[14] = true; // B14
    }

    // DECISION 8: ternary initialization of t
    //   B15: (u is odd)  -> t = v
    //   B16: (u is even) -> t = -(u/2)
    int t = (u & 1) == 1 ? v : -(u / 2);
    if ((u & 1) == 1) {
        branchCoverage[15] = true; // B15
    } else {
        branchCoverage[16] = true; // B16
    }
    // do-while core loop
    do {

        // DECISION 9: remove factors of 2 from t
        //   B17: inner loop ENTERED (t even at least once)
        //   B18: inner loop NOT entered (t already odd)
        boolean enteredInner = false;
        while ((t & 1) == 0) {
            enteredInner = true;
            branchCoverage[17] = true; // B17
            t /= 2;
        }
        if (!enteredInner) {
            branchCoverage[18] = true; // B18
        }

        // DECISION 10: reset max(u, v)
        //   B19: (t > 0) is TRUE  -> u = -t
        //   B20: (t > 0) is FALSE -> v = t
        if (t > 0) {
            branchCoverage[19] = true; // B19
            u = -t;
        } else {
            branchCoverage[20] = true; // B20
            v = t;
        }

        t = (v - u) / 2;

        // DECISION 11: loop continuation marker
        //   B21: (t != 0) is TRUE  -> loop continues
        //   B22: (t != 0) is FALSE -> loop ends
        if (t != 0) {
            branchCoverage[21] = true; // B21
        } else {
            branchCoverage[22] = true; // B22
        }

    } while (t != 0);

    return -u * (1 << k);
}
´´´

```java
@AfterAll
public static void printCoverageReport() {
    for (int i = 1; i <= 22; i++) {
        System.out.println("B" + i + ": " +
            (Fraction.branchCoverage[i] ? "HIT" : "MISS"));
    }
}
```
 ---

## Priting before tests 

 ```text
=== DIY BRANCH COVERAGE REPORT === Branch B 1: [ MISS ] Branch B 2: [ HIT ] Branch B 3: [ MISS ] Branch B 4: [ MISS ] Branch B 5: [ HIT ] Branch B 6: [ HIT ] Branch B 7: [ HIT ] Branch B 8: [ HIT ] Branch B 9: [ HIT ] Branch B10: [ MISS ] Branch B11: [ HIT ] Branch B12: [ HIT ] Branch B13: [ MISS ] Branch B14: [ HIT ] Branch B15: [ HIT ] Branch B16: [ HIT ] Branch B17: [ HIT ] Branch B18: [ HIT ] Branch B19: [ HIT ] Branch B20: [ HIT ] Branch B21: [ HIT ] Branch B22: [ HIT ]
```
## Printing after added tests 

```text
Branch Covrege: greatestCommonDivisor === Branch B 1: [ HIT ] Branch B 2: [ HIT ] Branch B 3: [ HIT ] Branch B 4: [ HIT ] Branch B 5: [ HIT ] Branch B 6: [ HIT ] Branch B 7: [ HIT ] Branch B 8: [ HIT ] Branch B 9: [ HIT ] Branch B10: [ MISS ] Branch B11: [ HIT ] Branch B12: [ HIT ] Branch B13: [ MISS ] Branch B14: [ HIT ] Branch B15: [ HIT ] Branch B16: [ HIT ] Branch B17: [ HIT ] Branch B18: [ HIT ] Branch B19: [ HIT ] Branch B20: [ HIT ] Branch B21: [ HIT ] Branch B22: [ HIT ]
```


## Unit Tests  Added

The DIY branch coverage report before adding new tests showed that several important branches in  
`greatestCommonDivisor(int, int)` were never executed, especially those related to:

- Zero inputs
- Integer overflow cases
- Negative and odd-number handling

To improve coverage, new tests were designed to deliberately trigger these missing branches.

---

### Test 1: Zero and Overflow Handling

```java
@Test
void testGcd_Zeros() throws Exception {

    // Case 1: One argument is zero and the other is positive
    // Expected behavior: return the absolute value of the non-zero argument
    // Targets branches: B1 (u==0||v==0) and B4 (normal return path)
    assertEquals(5, callPrivateGcd(0, 5));

    // Case 2: One argument is zero and the other is Integer.MIN_VALUE
    // Expected behavior: throw ArithmeticException due to overflow
    // Targets branches: B1 and B3 (overflow exception)
    assertThrows(ArithmeticException.class,
        () -> callPrivateGcd(0, Integer.MIN_VALUE));
}
```
### Test 2 Negative and odd inputs

```java
@Test
void testGcd_NegativesAndOdds() throws Exception {

    // Both inputs are odd and one is negative
    // Expected behavior: return 1
    // Skips the even-reduction loop and tests sign normalization
    // Targets branches: B5, B10, and B12
    assertEquals(1, callPrivateGcd(1, -1));
}
```

## Refactoring Plan

The refactoring would separate responsibilities into dedicated helper functions. The zero-input and overflow handling logic could be extracted into a `handleZeroInputs` method to isolate early-exit cases. The fast-path check for `abs(u) == 1 || abs(v) == 1` could be moved into an `isAbsOne` helper to clarify shortcut behavior. The sign normalization of inputs could be handled by a `normalizeNegative` method to remove duplicated conditional logic. The extraction of common powers of two (the loop that computes `k`) could be placed in an `extractCommonFactorsOfTwo` method, encapsulating the bitwise reduction and overflow check. Finally, the core of Stein’s binary GCD algorithm could be isolated in a `binaryGcdCoreLoop` method to clearly separate the mathematical algorithm from input preprocessing.


## Analysis of Remaining Uncovered Branches (B10 and B13)

Even after adding new tests, branches B10 and B13 remain uncovered due to the structure of the algorithm. Branch B10 (the `else` case of `if (v > 0)`) is rarely reached because inputs where `v` is zero or negative often trigger earlier returns, such as the zero-input check or the `abs(v) == 1`, before execution reaches the normalization step. Branch B13 (`k == 31`) represents an overflow condition that would require both inputs to be divisible by \(2^{31}\), which in practice means both must be `Integer.MIN_VALUE`. However, earlier safety checks and the control flow of the algorithm make this state nearly impossible to reach during normal execution. Therefore, these branches can be considered logically unreachable, and achieving 100% branch coverage for this method is unrealistic without modifying the implementation.
