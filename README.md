# Dinning-Philosopher-Problem

 Five philosophers regularly meet for dinner and to discuss the issues of the day. They always eat
spaghetti and they always eat with two forks. The diners are seated at a round table with a fork
between each plate. As a result, there are five philosophers with five forks. Each of the philosophers
wishes to eat dinner and each of them needs two forks to accomplish this task. In this assignment,
you will have to simulate each philosopher with an independent thread. Your goal is to write a system that
allows each philosopher to eat — nobody is permitted to starve to death.

Initially the philosophers are thinking, after a certain amount of time the philosopher gets hungry
and wishes to eat. Once eating is done, the philosopher then puts the fork down, so that they are
available for another neighbour.

If this system is coded without thought, each philosopher will immediately pick up, say, their left
fork followed by their right fork, thereby blocking others from eating and forming a deadlock
condition. A more intelligent approach will be needed. Develop the code to implement the given
scenario.

Your Output.txt file must contain sample runs that show the action of each philosopher at the table.
Each philosopher/thread must announce when he has picked up a fork (and which one: right or left),
when he has “eaten,” and when he has put down a fork. You should run the program long enough
to demonstrate that each philosopher has had the opportunity to eat at least once, and that dining
privileges have been distributed fairly.
