# Learning Outcomes

The goal of this lab is to get familiar with our work environment: 
the IntelliJ IDE, git, the pawtograder submission and grading system, the gradle build system, and the JUnit testing framework.

Note the lab is ungraded but you must complete it to successfully complete the other assignments in this course.

# IntelliJ IDE
 
**IntelliJ** is an integrated (program) development environment used by many professional Java programmers (as well as programmers using other programming languages).

The environment provides an editor, allows you to organize your work into several files that together comprise a project. 

There are several steps in getting started: 

   * Learn to organize your IntelliJ projects.

   * Learn to manage your files and save your work.

   * Learn how to edit your Java programs and run them, using the JUnit testing framework.
 
## Installation of Java 11 and IntelliJ

We will use Java 11 for this course. This is an older version but is supported by our submission server. Please download [JDK 11 here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).

Follow these steps to install IntelliJ:

   1. [Apply here](https://www.jetbrains.com/shop/eform/students) for an educational license for IntelliJ. Use your @northeastern.edu email address to sign up. 
   
   2. Download and install IntelliJ Ultimate Edition.
   
   3. When the educational license is approved, you can apply it to your IntelliJ installation by going to *Help --> Register* in the IntelliJ menu. 

# Git and SSH

## Connect GitHub with SSH

GitHub uses SSH to connect your local git repo with the remote
git repo in your GitHub profile. We need to setup SSH for this.
If you have already done this for your GitHub account then you can skip this step.
If you do not have SSH setup, follow the instructions in [here](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent).
Ask a TA for help if you get stuck.

Optional: To know more about SSH read [this](https://www.cloudflare.com/learning/access-management/what-is-ssh/).

## Configure SSO for SSH Key (Only needed to push code to pawtograder)

Once you have generated the SSH key and added it to your GitHub account you may need to authorize 
the key for SSO or single-signon access to the neu-cs5010 organization. 
The SSO setup is required to push code to the remote repo in pawtograder.

Access your GitHub settings:
- In the upper-right corner of any GitHub page, click your profile picture, then select "Settings."
Navigate to SSH and GPG keys:
- In the "Access" section of the sidebar, click on "SSH and GPG keys."
Configure SSO for the key:
- Locate the newly created SSH key in the list. To the right of that key, click "Configure SSO."
- In the dropdown menu that appears, locate the organization neu-cs5010. To the right of the organization name, click "Authorize."


## Set up The Local Repository

We are assuming that you have setup git, GitHub, SSH with GitHub, and logged into
pawtograder successfully. If you haven't completed this setup then you should review 
the "Technical Resources" document in Module 0 in Canvas under modules.

In the remote GitHub.com repository, look for the `Code` option and select 
the `SSH` option. Copy the repo address in the text and use it to clone the repo
using the command:

`$ git clone <COPIED-SSH-REPO-ADDRESS>`

Entering the above command in a terminal will download the repo in your 
current working directory as set in the terminal. The name of the directory
will be the same as the name of the remote repo by default unless you have changed the name 
explicitly during clone.

# The Gradle Project Structure in IntelliJ

Open IntelliJ and select the "Open" option, navigate to the directory that
has `pawtograder-setup-lab`, select it, and open the directory in the IDE.
Check it to see if the `build.gradle` file exists in the project. This 
means your project is now a gradle project.

Gradle is a build system that helps us perform several development tasks 
automatically. In this project the following tasks have been configured to run 
with gradle:

- check style based on a defined rule set, 
- compile code, 
- run unit tests with Junit,
- measure test quality

To run the gradle tasks, open `View -> Tool Windows -> Gradle` from the IntelliJ 
menu bar. This should open a pane with gradle tasks in a menu
on the left. Following is a list of gradle task that you will commonly use:

- `Tasks->build->clean` cleans the project and removes the `build/` directory.
- `Tasks->build->build` compiles code, runs checkstyle on src and test files, and executes all junit tests with jacoco coverage.
- `Tasks->verification->test` only runs all Junit tests with jacoco code coverage.
- `Tasks->verification->pitest` runs the PIT mutation test analysis. It will only run successfully if all tests pass.

The build system is configured as defined in`build.gradle`. 
When a gradle task in run, a report is generated and stored in the directory 
`build/reports`. The style report can be viewed by rendering the files 
`build/reports/checkstyle/main.html` (for src) and 
`build/reports/checkstyle/test.html` (for test) in a browser.

Similarly, the reports for the junit tests, the jacoco coverage report, and 
the mutation testing report can be viewed from the html files in `build/reports/tests`,
`build/reports/jacoco`, and `build/reports/pitest`.

# Code Coverage

How do we know if we have written enough tests? One method is to use code coverage.
Any code coverage tool will run a given suite of tests on the source code and will
attempt to determine if all parts of the source code are being tested by the test suite.
A coverage tool generates a report highlighting parts of the code that either have
missing tests or have no tests at all. A developer can analyze the report and use it
to guide their testing process. In this lab (and this course generally) we use two coverage tools -- jacoco and
PIT. We use two tools because they use different techniques to measure code
coverage. PIT uses mutation testing which is more effective than jacoco for 
finding untested code. However, jacoco gives us a quick initial understanding 
of the strength and completeness of our tests. 

The jacoco tool reports two metrics that are of interest -- branch and line coverage.
The branch coverage percentage in a jacoco report indicates the percentage 
of branches in the source that have been executed at least once by the tests.
The line coverage percentage indicates the percentage of lines in the source
that have been executed by the given tests. Ideally want 100% on both. However,
100% branch and line coverage does not imply that are tests execute all possible
paths in our code. It simply means we have a strong degree of confidence in the 
completeness of our tests.

PIT is coverage tool based on the concept of mutation testing. 
It works by creating incorrect versions of a given implementation 
and running the defined test suite against all generated incorrect 
implementations. If the defined test suite fails against all incorrect 
implementations, that is, kills all mutants then it's considered a good test 
suite.

The PIT mutation report reports two metric of interest to us -- mutation coverage
and test strength. To understand the meaning of the metrics we must first
understand the following terms:

1. **Mutant Killed** : At least one test case failed for that incorrect mutant
2. **Mutant Survived** : No test case failed for the incorrect mutant.
3. **No Coverage** : No test case was executed for the incorrect mutant.

Coming back to the meaning of the metrics:

- **Test Strength** : This is the ratio of the number of mutants killed by the number of mutants executed when the test suite was run.
- **Mutation Coverage** : This is the ratio of the number of mutants killed by the total number of mutants generated.

A low test strength implies that all tests in the current test suite 
pass even if parts of our implementation are mutated incorrectly. 
For e.g., if we incorrectly change an if condition in one of our methods, 
all our tests pass. Clearly, at least one test should have failed as a 
result of this incorrect change. If all our tests pass despite incorrect 
changes or mutations to our code, it means two things -- 
(1) we need to write more tests to cover those parts or 
(2) these parts of the code are redundant and should be removed. 

On the other hand, a low mutation coverage implies that there are 
parts of our code for which we haven't written any tests at all. 
Hence, we should write more tests.

We should inspect the mutation coverage report carefully and aim to achive
100% mutation and test strength coverage. Sometimes this may not be possible
because a mutant may not be a real fault, that is, it is behaviorally equivalent
to the original program. While PIT can generate behaviorally equivalent 
mutants, this is not common, and you should carefully analyze a mutant before
declaring it behaviorally equivalent.

# For You To Do

## Configure IDE With Checkstyle

1. Go to IntelliJ's settings (File menu on Windows or Apple Menu on Mac).
2. Search for "plugins" and select it.
3. Search for "CheckStyle-IDEA" in the marketplace and install it.
4. Restart IntelliJ.
5. Go to *Editor -> Code Style -> Java*, or search for *code style*.
6. Click on the Gear icon next to *Scheme*, select *Import Scheme* and then *Checkstyle Configuration*. Point it to `config/checkstyle/checkstyle.xml`, and press *OK*.
7. Verify configuration by selecting a Java file and then selecting `Code -> Reformat Code` from the IntelliJ menu bar.
This will automatically format the code as per the style rules in `config/checkstyle/checkstyle.xml`.


## Run Gradle Tasks

Run the gradle clean and build task. This will run checkstyle
on your code, run all the tests, and generate the Jacoco report.

If build fails, it is likely that you have either failed 
the style check or the tests. The console output will tell you
why the build failed.

If build failed to style errors, navigate to `build/reports/checkstyle`
and fix the reported style errors. The HTML
files `main.html` and `test.html` show 
the report for src and test files, respectively.

## Generate Javadocs

The provided files already have Javadoc-style comments. You will now generate html documentation from these files from within IntelliJ.


1. Within your IntelliJ project folder, create a new folder called ''docs". IntelliJ will pull all the generated documentation within this folder.

2. From the IntelliJ menu, choose *Tools --> Generate Javadoc...*.

3. In the *Output Directory* field, point it to the *docs* folder you created in step 1.

4. Press *OK*. IntelliJ will now generate the html files and open them in your browser. If the files do not open automatically, go to the *docs* folder and open `index.html` in your browser.

5. Correlate the Javadoc comments in the `Person.java` file with the output in `Person.html` file to see how Javadoc comments affect the documentation.

The Java SDK comes with a tool called *javadoc*.
IntelliJ uses this tool and passes it all your `.java` files.
You will find this tool in the 'bin' folder inside your Java SDK directory
(wherever you installed it on your machine).

## Improve Coverage

Run the clean and build task in gradle. This will generate a
jacoco report in `build/reports/jacoco`. Open the `index.html`
file in a browser and inspect the branch and line/instruction
coverage. Ask a TA to help if you do not understand or open the
report.

Based on the coverage explanation provided in the *Code Coverage*
section, improve the branch and line coverage by adding more tests
in `test/PersonTest.java` for the `src/main/java/Person.java` file.
If the tests are for `Book.java` write those tests in `test/BookTest.java`.

Once you have made the branch and line coverage in jacoco to 100%,
Run the PIT mutation testing tool using the gradle task for `pitest`.
Analyze the report in `build/reports/pitest`. Open the `index.html` file
and inspect mutation coverage and test strength. They should be 100%.
If they are not, you need to add more tests.

## Submit code to pawtograder

Submitting code to pawtograder is similar to pushing code to a git repository.
First, read the instructions [here](https://docs.pawtograder.com/students/assignments/create-submission)
before proceeding.

Open the terminal in your IntelliJ IDE (`View ->Tool Windows -> Terminal`).
In the terminal when you are ready to submit code do the following:
- Add file to your git repo locally
      `$ git add <file-path>` 
- Commit files to staging
      `$ git commit -m "Meaningful message describing the changes made"`
- Push to remote repo in Pawtograder
      `$ git push`

If your push is rejected it most likely because you haven't setup
SSH with git or do have configured your SSH key with SSO for neu-cs5010. Go back to the SSH setup instructions.

## Take the Self Review

As part of this lab, you will reflect on this exercise by answering a 
few questions. While this review is ungraded, you should answer
the questions sincerely as this is what you will be doing in an 
assignment, where the self review will be graded.

Read this [document](https://docs.pawtograder.com/students/assignments/self-review) to understand how to take the self review on 
pawtograder our submission system.
