# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer:
In my implementation I didn't come up with a formula to find the position for each and all my methods were mixed together. However in the solution at the end of lab we split the task required into clearer smaller task and we used formulas to easily find the positions.
-----

**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer:
Tessellating hexagons is similar to randomly generating a world using rooms and hallways as we can find a repeated pattern to efficiently create a world with rooms and hallways. In this case the rooms and hallways act similarly to the hexagons and we can fit them together.
-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer:
Methods to make the rooms/hallways. Methods for positioning the objects. 
-----
**What distinguishes a hallway from a room? How are they similar?**

Answer:
The width of rooms is random whereas the width of hallyway is fixed. They both must have walls that are distinct from floors and they both must be connected.