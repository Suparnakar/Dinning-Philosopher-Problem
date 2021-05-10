Each Philosopher has 3 states. Thinking, Hungry, Eating.

Each Philosopher has 2 forks. Left fork, right fork.

Philosophers number range is 0 to 4.
Forks number range is 0 to 4.

Philosophers has sit in clockwise position.
Forks are distributed in clockwise direction.

Philosopher n will have n-th fork to his left and (n-1)th fork to his right.

0th Philosopher has fork no 0 (left) and 4 (right).
1st Philosopher has fork no 1 (left) and 0 (right).
2nd Philosopher has fork no 2 (left) and 1 (right).
3rd Philosopher has fork no 3 (left) and 2 (right).
4th Philosopher has fork no 4 (left) and 3 (right).

The output is shown here as in 1 minute approximately.

If a philosopher can't grab left fork, it will remain hungry and wait.
If he can, he will try to grab right fork also.
If he can grab both fork, it will eat.
If he can't grab right fork, he will release the left fork and wait for some time before trying to grab again.

This program will run indefinitely. Philosophers will announce after eating at least once.

