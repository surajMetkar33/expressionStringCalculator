* String expression calculator program *
* @Author: Suraj Metkar *

::: Rules :::
1) operands can be number between 0-9
2) Opening bracket should be preceded by operator or other opening bracket except for the start of expression.
3) Expression cannot have consecutive digit or operators
4) A mathematical expression cannot start or end with a operator with only exception for ( (expression can start but cannot end) and ) (expression can end but cannot start). Every opening brace ( must have a corresponding closing brace ) . An operand cannot have its adjacent as a operator except for ( i.e (( and ) i.e ))

-===Input===-
4
7+(6*5^2+3-4/2)
7+(67(56*2))
8*+7
(8*5/8)-(3/1)-5

-===Output===-
Case #1: 158
Case #2: INVALID EXPRESSION
Case #3: INVALID EXPRESSION
Case #4: -3