# Report for Assignment 3

This is a template for your report. You are free to modify it as needed. It is
not required to use Markdown, but the report has to be delivered in a standard,
cross-platform format.

## Project

Name: Apache Commons Lang

URL: https://github.com/apache/commons-lang

Apache Commons Lang is a Java library that adds extra utility methods on top of the standard java.lang API. It covers things like string manipulation, basic math, object reflection, and system properties, and is maintained by the Apache Software Foundation.

## Onboarding Experience

Did it build and run as documented?

See the assignment for details; if everything works out of the box, there is no
need to write much here. If the first project(s) you picked ended up being
unsuitable, you can describe the onboarding experience for each project, along
with reasons why you changed to a different one.

1. **How easily can you build the project? Briefly describe if everything worked
   as documented or not.**

   **(a) Did you have to install a lot of additional tools to build the
   software?**

   Not really. Since the group has worked with Java and Maven before, we already
   had the necessary tools installed.

   **(b) Were those tools well documented?**

   N/A.

   **(c) Were other components installed automatically by the build script?**

   Many dependencies were installed automatically by Maven.

   **(d) Did the build conclude automatically without errors?**

   Since a `report.md` was included in the project and the recommended command
   to check that all tests and checks pass is `mvn`, the following error
   occurred:

   ```bash
   Assignment-3-CodeComplex-Coverage % mvn
   [INFO] Scanning for projects...
   [INFO]
   [INFO] ------------------< org.apache.commons:commons-lang3 >------------------
   [INFO] Building Apache Commons Lang 3.21.0-SNAPSHOT
   [INFO]   from pom.xml
   [INFO] --------------------------------[ jar ]---------------------------------
   [INFO]
   [INFO] --- clean:3.5.0:clean (default-clean) @ commons-lang3 ---
   [INFO]
   [INFO] --- enforcer:3.6.2:enforce (enforce-maven-version) @ commons-lang3 ---
   [INFO] Rule 0: org.apache.maven.enforcer.rules.version.RequireMavenVersion passed
   [INFO]
   [INFO] --- enforcer:3.6.2:enforce (enforce-java-version) @ commons-lang3 ---
   [INFO] Rule 0: org.apache.maven.enforcer.rules.version.RequireJavaVersion passed
   [INFO]
   [INFO] --- apache-rat:0.17:check (rat-check) @ commons-lang3 ---
   [WARNING] Basedir is : Assignment-3-CodeComplex-Coverage
   [INFO] Excluding patterns: site-content/**, src/site/resources/.htaccess,
   src/site/resources/download_lang.cgi, src/site/resources/release-notes/RELEASE-NOTES-*.txt,
   src/test/resources/lang-708-input.txt, **/*.svg, **/*.xcf, site-content/**,
   .checkstyle, .fbprefs, .pmd, .asf.yaml, .gitattributes, src/site/resources/download_*.cgi,
   maven-eclipse.xml, .externalToolBuilders/**, .vscode/**, .project, .classpath,
   .settings/**, **/*.svg, **/*.xcf
   [INFO] Excluding MAVEN collection.
   [INFO] Excluding ECLIPSE collection.
   [INFO] Excluding IDEA collection.
   [INFO] Processing exclude file from STANDARD_SCMS.
   [INFO] Excluding STANDARD_SCMS collection.
   [INFO] Excluding MISC collection.
   [INFO] RAT summary:
   [INFO]   Approved:  573
   [INFO]   Archives:  0
   [INFO]   Binaries:  4
   [INFO]   Document types:  4
   [INFO]   Ignored:  36
   [INFO]   License categories:  2
   [INFO]   License names:  2
   [INFO]   Notices:  3
   [INFO]   Standards:  574
   [INFO]   Unapproved:  1
   [INFO]   Unknown:  1
   [ERROR] Unexpected count for UNAPPROVED, limit is [0,0].  Count: 1
   [INFO] UNAPPROVED (Unapproved) is a count of unapproved licenses.
   [WARNING] *****************************************************
   Generated at: 2026-02-13T15:51:12+01:00

   Files with unapproved licenses:
     /report.md
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD FAILURE
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  5.158 s
   [INFO] Finished at: 2026-02-13T15:51:16+01:00
   [INFO] ------------------------------------------------------------------------
   [ERROR] Failed to execute goal org.apache.rat:apache-rat-plugin:0.17:check (rat-check)
   on project commons-lang3: Counter(s) UNAPPROVED exceeded minimum or maximum values.
   See RAT report in: 'Assignment-3-CodeComplex-Coverage/target/rat.txt'. -> [Help 1]
   [ERROR]
   [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
   [ERROR] Re-run Maven using the -X switch to enable full debug logging.
   [ERROR]
   [ERROR] For more information about the errors and possible solutions, please read
   the following articles:
   [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
   ```

   This error is caused by `org.apache.rat:apache-rat-plugin`, which audits the
   licenses of files in the repository. Since `report.md` is not licensed, the
   build fails. To fix this, the following line was added to the `pom.xml` in the
   RAT exclude list:

   ```xml
   <plugin>
     <groupId>org.apache.rat</groupId>
     <artifactId>apache-rat-plugin</artifactId>
     <configuration>
       <inputExcludes>
         <inputExclude>**/report.md</inputExclude>
         <inputExclude>site-content/**</inputExclude>
         <inputExclude>src/site/resources/.htaccess</inputExclude>
         <inputExclude>src/site/resources/download_lang.cgi</inputExclude>
         <inputExclude>src/site/resources/release-notes/RELEASE-NOTES-*.txt</inputExclude>
         <inputExclude>src/test/resources/lang-708-input.txt</inputExclude>
         <inputExclude>**/*.svg</inputExclude>
         <inputExclude>**/*.xcf</inputExclude>
       </inputExcludes>
     </configuration>
   </plugin>
   ```

   This excludes `report.md` from the license check and allows the build to pass.

   **(e) How well do examples and tests run on your system(s)?**

   Running the full build (tests and checks) took 18:52 minutes to complete.

2. **Do you plan to continue or choose another project?**

## Complexity

### Function: `Dateutils::iterator`

**Lizard tool results:**
iterator> ng3\text\StrSubstitutor.java
      66     18    374      2      71 DateUtils::iterator@973-1043@.\src\main\java\org\apache\commons\lang3\time

**Manual CC count:**
18

**Questions:**

1. **Did tool and manual count get the same result?**  
   The manual counting for CC gave the same result as the tool Lizard, the formula for calculating it was derived from: https://en.wikipedia.org/wiki/Cyclomatic_complexity 
2. **Are the functions long as well as complex?**  
   The function is relatively complex, and moderartely long (around 60 lines).
3. **What is the purpose of the function?**  
   The purpose of this function is to compute start and end Calendar dates for a given range style, the high CC can be attributed to multiple modes needing more switch statements as well as need for normalisations, leading to using more if statements. 
4. **Are exceptions taken into account?**  
   No, the exceptions are not taken into account.
5. **Is the documentation clear about all possible outcomes?**  
   The documentation for the function only provides a general overview of the functions purpose, inputs and outputs.

## Refactoring
Since the code computes start and end dates for either month format or week format, splitting the month and week logic into two separate functions can decrease the CC - like buildMonthRange and buildWeekRange. Each method handles only its own date calculations and cutoff adjustments. The main iterator method then just calls the appropriate builder based on the range style. This removes the nested switch statements and reduces branching in each method. As a result, the code becomes easier to read, test, and maintain and lower CC.

## Coverage
1. **What is the quality of your own coverage measurement? Does it take into account ternary operators (condition ? yes : no) and exceptions, if available in your language?** The tool is very basic and is implemented specific to the function, the ternary operators are not automatically detected - they need to be instrumented
2. **What are the limitations of your tool? How would the instrumentation change if you modify the program?**
   One limitation is it is not easily scalable for more functions, the tool was only implemented for the specific function in question and the source code would need to be modified for any changes. Consequently, it is also more error prone.
3. **Are the results of your tool consistent with existing coverage tools?**  
   The results are relatively consistent when comparing with the coverage tool Jacoco, which resulted in a coverage of 90% vs the manual implementations result of 92%

## Coverage Improvement
Coverage improvement was done for isCreatable as the two uncovered branches in the previously chosen function iterator were unreachable. The coverage improvement increased from 84% to 92% with two new tests added.

## Complexity

### Function: `StringUtils::splitWorker`

**Lizard tool results:**

| Overload | NLOC | CCN (lizard) | Lines |
|----------|------|--------------|-------|
| `String, char, boolean` | 32 | 10 | 7588-7620 |
| `String, String, int, boolean` | 78 | 23 | 7632-7715 |

**Manual CC count:**

Using the formula: **CC = (decision points) - (exit points) + 2**

splitWorker(String, char, boolean) = 9 - 3 + 2 = 8 (Lizard CC = 10)
splitWorker(String, String, int, boolean) = 22 - 3 + 2 = 21 (Lizard CC = 23)
**Questions:**

1. **Did tool and manual count get the same result?**  
   No. Lizard reports CC=10 and CC=23, while manual counts give CC=8 and CC=21. There could be multiple explanations for the difference of 2, such as lizard could be counting the while loop exit condition as an additional decision point, or maybe uses a different formula.
2. **Are the functions long as well as complex?**  
   Yes. The first overload is 32 NLOC with CC=10, and the second is 78 NLOC with CC=23. Both are longer than average functions.
3. **What is the purpose of the function?**  
   `splitWorker` splits a string into an array of substrings based on a separator. It provides the logic for `split()` and  `splitPreserveAllTokens` methods in `StringUtils`. The high CC comes from handling three cases: whitespace, single-char, multi-char separators.

4. **Are exceptions taken into account?**  
   No exceptions are thrown or caught in `splitWorker`. The function handles edge cases via early returns rather than exceptions.

5. **Is the documentation clear about all possible outcomes?**  
   The Javadoc describes the main parameters and return value, but does not explicitly document all branch outcomes. For example, the exact behavior when `max` is reached mid-string is missing.

## Refactoring

#### Plan for refactoring complex code

**First overload (`splitWorker(String, char, boolean)`):**

Delegate to the second overload. The single-char path already handles this case, so there's no need for duplicate code.

**Second overload (`splitWorker(String, String, int, boolean)`):**

Extract the three tokenization paths into separate methods:
- `tokenizeByWhitespace()` 
- `tokenizeBySingleChar()` 
- `tokenizeByMultiChar()` 
- `finalizeSplitResult()` 

#### Estimated impact of refactoring

| Method | Before | After | Reduction |
|--------|--------|-------|-----------|
| `splitWorker(char)` | CC=10 | CC=1 | **90%** |
| `splitWorker(String)` | CC=23 | CC=5 | **78%** |

**Benefits:**
- Single Responsibility Principle 
- DRY (Don't Repeat Yourself)
- Testability - Each tokenization path can be tested independently 
- Readability - The main method is now a simple dispatcher

**Drawbacks:**
- Code spread across more methods
- Original was "performance tuned for JDK 1.4" - might lose micro-optimizations

#### Carried out refactoring (P+)

All existing tests pass after refactoring.

```bash
git diff master -- src/main/java/org/apache/commons/lang3/StringUtils.java
```



## Coverage

### Tools

JaCoCo was used for automated coverage measurement. It was already integrated into the Maven build via the `jacoco-maven-plugin`. Running `mvn test jacoco:report -Drat.skip=true` generates an HTML report.
JaCoCo was well-documented and easy to use since it was pre-configured in the project. No additional setup was required.

### Your Own Coverage Tool

I implemented manual branch coverage instrumentation for `splitWorker` by adding a static boolean array `SPLITWORKER_COVERAGE[38]` to `StringUtils.java`. Each branch outcome sets a flag when executed.

**Git diff command:**
- Added `SPLITWORKER_COVERAGE[38]` array and helper methods
- Instrumented both `splitWorker` methods with `SPLITWORKER_COVERAGE[n] = true;` at each branch
- Added `@AfterAll` in `StringUtilsTest.java` to print coverage after tests


It supports:
- `if`/`else` branches
- `while` loop entry
- Short-circuit `||` and `&&` operators (partial - we track when the combined condition is true)
- Early `return` statements

**How to use:**
1. Run tests normally
2. There is an included @AfterAll method that prints the coverage report to the console

### Evaluation

1. **How detailed is your coverage measurement?**  
   I track 38 branch outcomes across both `splitWorker` overloads. I report HIT/MISS for each branch and a total percentage.

2. **What are the limitations of your own tool?**  
   - It only covers `splitWorker` methods
   - It does not track individual operator outcomes separately (e.g., `a || b` is only tracked as one condition, not two)
   - Any refactoring breaks the instrumentation
   - Need to call print method manually

3. **Are the results of your tool consistent with existing coverage tools?**  
   JaCoCo Branch Coverage: splitWorker(String, char, boolean) = 62% and splitWorker(String, String, int, boolean) 62% 
   DIY Tool: splitWorker(String, char, boolean) = 71% (10/14 branches) and splitWorker(String, String, int, boolean) = 88% (21/24 branches)

   The results are mostly consistent but JaCoCo returns slightly worse coverage. This could be because JaCoCo tracks more fine-grained branch outcomes (e.g., each `||` and `&&` operand separately)
 
## Coverage Improvement
### Baseline Coverage (before new tests)

| Method | DIY Tool | JaCoCo Branch |
|--------|----------|---------------|
| `splitWorker(String, char, boolean)` | 71% (10/14) | 62% |
| `splitWorker(String, String, int, boolean)` | 88% (21/24) | 62% |

### New Coverage (after new tests)

| Method | DIY Tool | JaCoCo Branch |
|--------|----------|---------------|
| `splitWorker(String, char, boolean)` | **100% (14/14)** | ~95% (improved from 71%) |
| `splitWorker(String, String, int, boolean)` | **100% (24/24)** | 100% (improved from 63%) |

Note: JaCoCo counts more branches than our DIY tool because it tracks each `||` and `&&` operand separately.

### Test cases added (6 tests - P+ requirement met)

testSplitWorkerCharNull: null input returns null
testSplitWorkerCharEmpty: empty input returns empty array
testSplitWorkerCharFinalConditionFalse: string ending with separator
testSplitWorkerStringSingleCharMax: max limit with single-char separator
testSplitWorkerStringMultiCharMax: max limit with multi-char separator
testSplitWorkerStringWhitespaceMax: max limit with whitespace separator

**Git diff command:**
```bash
git diff master -- src/test/java/org/apache/commons/lang3/StringUtilsTest.java
```

Number of test cases added: 6.


# Analysis of Fraction::greatestCommonDivisor(int, int)

---
OLIVIA STRÖMBOM

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

# Analysis of StringUtils::replaceEach(String, String[], String[], boolean, int)

Sofia Andersson

---

## 1.  Locator

```java
StringUtils::replaceEach@6426-6543@./src/main/java/org/apache/commons/lang3/StringUtils.java
```

---

## 2. Function Source Code

```java
private static String replaceEach(
        final String text, final String[] searchList, final String[] replacementList, final boolean repeat,
        final int timeToLive) {

    // Performance note: This creates very few new objects (one major goal)
    // let me know if there are performance requests, we can create a harness to measure
    if (isEmpty(text) || ArrayUtils.isEmpty(searchList) || ArrayUtils.isEmpty(replacementList)) {
        return text;
    }

    // if recursing, this shouldn't be less than 0
    if (timeToLive < 0) {
        throw new IllegalStateException("Aborting to protect against StackOverflowError - " +
                "output of one loop is the input of another");
    }

    final int searchLength = searchList.length;
    final int replacementLength = replacementList.length;

    // make sure lengths are ok, these need to be equal
    if (searchLength != replacementLength) {
        throw new IllegalArgumentException("Search and Replace array lengths don't match: "
                + searchLength
                + " vs "
                + replacementLength);
    }

    // keep track of which still have matches
    final boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

    // index on index that the match was found
    int textIndex = -1;
    int replaceIndex = -1;
    int tempIndex;

    // index of replace array that will replace the search string found
    // NOTE: logic duplicated below START
    for (int i = 0; i < searchLength; i++) {
        if (noMoreMatchesForReplIndex[i] || isEmpty(searchList[i]) || replacementList[i] == null) {
            continue;
        }
        tempIndex = text.indexOf(searchList[i]);

        // see if we need to keep searching for this
        if (tempIndex == -1) {
            noMoreMatchesForReplIndex[i] = true;
        } else if (textIndex == -1 || tempIndex < textIndex) {
            textIndex = tempIndex;
            replaceIndex = i;
        }
    }
    // NOTE: logic mostly below END

    // no search strings found, we are done
    if (textIndex == -1) {
        return text;
    }

    int start = 0;

    // get a good guess on the size of the result buffer so it doesn't have to double if it goes over a bit
    int increase = 0;

    // count the replacement text elements that are larger than their corresponding text being replaced
    for (int i = 0; i < searchList.length; i++) {
        if (searchList[i] == null || replacementList[i] == null) {
            continue;
        }
        final int greater = replacementList[i].length() - searchList[i].length();
        if (greater > 0) {
            increase += 3 * greater; // assume 3 matches
        }
    }
    // have upper-bound at 20% increase, then let Java take over
    increase = Math.min(increase, text.length() / 5);

    final StringBuilder buf = new StringBuilder(text.length() + increase);

    while (textIndex != -1) {

        for (int i = start; i < textIndex; i++) {
            buf.append(text.charAt(i));
        }
        buf.append(replacementList[replaceIndex]);

        start = textIndex + searchList[replaceIndex].length();

        textIndex = -1;
        replaceIndex = -1;
        // find the next earliest match
        // NOTE: logic mostly duplicated above START
        for (int i = 0; i < searchLength; i++) {
            if (noMoreMatchesForReplIndex[i] || isEmpty(searchList[i]) || replacementList[i] == null) {
                continue;
            }
            tempIndex = text.indexOf(searchList[i], start);

            // see if we need to keep searching for this
            if (tempIndex == -1) {
                noMoreMatchesForReplIndex[i] = true;
            } else if (textIndex == -1 || tempIndex < textIndex) {
                textIndex = tempIndex;
                replaceIndex = i;
            }
        }
        // NOTE: logic duplicated above END

    }
    final int textLength = text.length();
    for (int i = start; i < textLength; i++) {
        buf.append(text.charAt(i));
    }
    final String result = buf.toString();
    if (!repeat) {
        return result;
    }

    return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
}
```

---

## 3. Complexity Analysis

### 3.1 Tool-based Measurement (lizard)

Command used:

```bash
lizard -l java src/main/java/org/apache/commons/lang3/StringUtils.java | grep replaceEach
```

Output:

```text
80     29    594      5     118 StringUtils::replaceEach@
```

Interpretation:

- CCN = 80
- NLOC = 29
- Tokens = 595
- Parameters = 5
- Length ≈ 118 lines

---

### 3.2 Manual Cyclomatic Complexity Count

Decision points counted:

- if statements
- while loops
- for loop

According to my count there are 28 decision points. The McCabe formula for cyclomatic complexity is $CC=1+\pi$ where $\pi$ is the number of decision points. Thus the cyclomatic complexity is 29. This matches lizard.

More details:\
[github.com/group-17-dd2480/Assignment-3-CodeComplex-Coverage/issues/6](https://github.com/group-17-dd2480/Assignment-3-CodeComplex-Coverage/issues/6)

---

## 4. Coverage Analysis

I used VsCodes Java test library coverage. It showed good coverage in the branch.

## 5. Coverage Improvement (Tests Added)

Branch:

```bash
git checkout features/tests-for-StringUtils-replaceEach
```

```java
// Replace String with the same String
assertEquals(StringUtils.replaceEach("aba", new String[] { "a" }, new String[] { "a" }), "aba");
// Replace a String that is not in the String
assertEquals(StringUtils.replaceEach("aba", new String[] { "c" }, new String[] { "d" }), "aba");
```

 ---

## Refactoring Plan

`replaceEach` function can be refactored by moving a part into a different function. In the function there is a `for` loop that is duplicated in the code, it is surrounded by these comments. It could also be divided into several different functions for the different parts. Ex first it has several checks whether the input is valid, this could easily be divided into a separate function.

```java
// NOTE: logic mostly duplicated above START
...
// NOTE: logic duplicated above END
```

Finding a way to only have it written once, maybe by replacing the while loop with a do/while loop.


## Analysis of `shift([]boolan, int, int, int)`
**Function :** shift([]boolan, int, int, int)  
(For implementation, and in-depth analysis: https://github.com/group-17-dd2480/Assignment-3-CodeComplex-Coverage/issues/8)

**ArrayUtils::shift@7142-7173@./src/main/java/org/apache/commons/lang3/ArrayUtils.java**

---

1. **What are your results for the complex function?**  
   a. **Did all methods (tools vs. manual count) get the same result?**  
   Yes, the CC reported by the lizard tool was 10, and the manual calculations also show 10.  

   b. **Are the results clear?**  
   Yes.  

2. **Is the function just complex, or also long?**  
   Both, LOC can correlate with higher complexity. E.g. more conditional statements will increase complexity and LOC. However, redundant assignments and abstractions will increase LOC without increasing complexity but this is less common.  

3. **What is the purpose of the function?**  
   The function swaps a contiguous segment of an array in place by an offset. Eg. given  
   `boolean[] boolArray = {true, false, false, false};`

   | Input                          | Output                          |
   |--------------------------------|----------------------------------|
   | `shift(boolArray, 0, 2, 1);`   | `[false, true, false, false]`   |
   | `shift(boolArray, 0, 3, 1);`   | `[false, true, false, false]`   |
   | `shift(boolArray, 0, 3, 2);`   | `[false, false, true, false]`   |
   | `shift(boolArray, 0, 4, -1);`  | `[false, false, false, true]`   |

4. **Are exceptions taken into account in the given measurements?**  
   Yes.  

5. **Is the documentation clear with regards to all the possible outcomes?**  
   No.  




### SEMAT

According to the Essence standard, our team is currently in the “In Place” state of the Way of Working. We have established a shared and documented way of working that is understood and consistently followed by all group members. Because we already have good familiarity with Java, Maven, and Git, onboarding into the project and setting up the workflow went smoothly. We use Git for version control, Maven for building and running tests, and tools such as JaCoCo and Lizard that where required for this lab where easy to use due to how we used java and maven before. Responsibilities are clearly divided, and collaboration is handled through branches and pull requests, which helps ensure that changes are communicated and reviewed within the team.

To progress to the “Working Well” state, the main obstacles are efficiency and merging. Some activities, such as manual branch instrumentation and coverage analysis, are still time-consuming. But we all agree that we are going towards a more positive direction with each lab becoming easier to work with as a team. 


