2018/11/28
Learn AC by Emanuel Carvalho - eprincip@uoguelph.ca
Project Implementation for CIS*6120

# To execute the program, give the name of the dataset file as argument.

## Input details
The inputfile must have a header paragraph consisting of 4 integers
separated by spaces or tabs:
r c ke kp
r is the number of rows/samples in the dataset.
c is the number of columns/variables, all binary.
ke is the edge penalty, must be positive.
kp is the parameter penalty, must also be positive.
The following lines are the data matrix, consisting of only 0 and 1.

## Execution and Output details
The program will generate the marginal distributions for each variable.
An output with the same name of the input file will be generated with the
.marg extension, the first column corresponds to the value 0 and the
second to 1.

The next step is the generation of the arythmetic circuit using the the
marginal distributions for its leaves gates. Then it will greedly try to
split nodes until a local maximum is reached. Notice that the order of
operations is the same, and no randomness was used in this version, so
algorithm is deterministic.

After finishing the learning steps, an output file with the same name
of the input file will be generated with extension .out. This file contains
an in-order representation of the tree, with numbers representing the
nth variable, sum gates represented by the + operator and product gate
represented by the * operator. The next lines are a matrix with k+2 columns:
-the first k columns are 0s or 1s representing the value of each variable
-after that there is a double for the output of the AC with said configuration
-the last one is the frequency of that configuration in the original dataset
for comparison.

-Lastly, I included the housevotes84 adapted dataset that i used for testing.
I advise to not use all of the variables and limit the the selection in
the columns number input as the output file grows exponentially.
