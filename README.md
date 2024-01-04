
# The Land of Erehwon

In the land of Erehwon, which can be represented using a square grid, there are two competing factions, the Reds and the Blues. Each cell in the Erehwon grid is occupied by either a Red or a Blue or is vacant. 

Any resident of Erehwon is happy if and only if a certain fraction of their neighbours are like them (Red or Blue). The neighbourhood of a cell $C$ is all the cells that can be reached in a fixed number of steps from $C$ and one can travel vertically, horizontally or diagonally for each step. If the neighbourhood is the set of cells that are one step away then $C$ may have up to 8 neighbours. If the happiness threshold is 0.375 and $C$ has 8 neighbouring cells then the resident of $C$ is happy if and only if 3 of the neighbouring cells are occupied by someone from the same faction.

An unhappy person simply decides to move. At time $t$, if there are 8 vacant cells and 7 unhappy people then each of them moves to one of the vacant cells (chosen at random). If the number of vacant cells is fewer than the number of unhappy people then one first selects as many people as there are vacant slots and then moves these people to those vacant slots, at random.

Simulation of movements of residents in Erehown shown using RedBlueGrid GUI. Implemented method to achieve maximal level of happiness at Erehwon given any randomized starting layout.


