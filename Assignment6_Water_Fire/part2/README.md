<img src="./media/water.gif" align="right" width="500px"/>

# Assignment - Water 

> "Let there be...water!"


## Implementation Logistics

**For this Lab/Assignment**: You will be working on your own laptop/desktop machine.

- My strong recommendation is that everyone uses a linux environment or a virtual machine with linux.
	- If you are fairly experienced, you may use whatever operating system, IDE, or tools for completing this lab/assignment.
	- Again, however, my instructions will usually be using the command-line, and that is what I will most easily be able to assist you with.
	- In the future there may be restrictions, so please review the logistics each time.

# Resources to help

- C++ Related
	- A Standard Tutorial: http://www.cplusplus.com/doc/
	- The ISO Standard for the language: https://isocpp.org
		- Typically the latest and greatest information will always be here.
	- Some example compiler flags
		- https://bytes.usc.edu/cs104/wiki/gcc/
- SFML Related
    - https://www.sfml-dev.org/tutorials/2.5/graphics-sprite.php
    - [My playlist on SFML](https://www.youtube.com/playlist?app=desktop&list=PLvv0ScY6vfd95GMoMe2zc4ZgGxWYj3vua)
    	- Useful for the fundamentals and setup of the libraries.    
 - Inspiration of this assignment
    - [Archived Link](https://web.archive.org/web/20160418004149/http://freespace.virgin.net/hugo.elias/graphics/x_water.htm)

# Assignment strategy and tips

- Read the whole assignment before starting
- If you make a mistake you can always revert back in your github histoy.
	- Thus I recommend committing and pushing your files often as you make changes.

# Description

In this assignment, you are going to be recreating another classic effect creating water using a common computer graphics technique known as 'area sampling'. [Sampling](https://en.wikipedia.org/wiki/Sampling) itself is a statistical technique for gathering data. The technique we are going to use, is going to be to sample from neighboring pixels, and propogate a 'wave' like effect (See the image at the top of the assignment for an example). Some folks claim that this technique itself was created by accident (thus the importance of playing), regardless, it is a neat effect and will help us exercise our knowledge of 2D arrays.

There is a wonderful write up of the effect you will be creating here: https://www.gamedev.net/tutorials/programming/graphics/the-water-effect-explained-r915/ [archived link](https://web.archive.org/web/20221011234140/https://www.gamedev.net/tutorials/programming/graphics/the-water-effect-explained-r915/). Additionally, the inspiration for this assignment is an [archived link here](https://web.archive.org/web/20160418004149/http://freespace.virgin.net/hugo.elias/graphics/x_water.htm)

Some skills you will be exercising:

1. Using and linking the SFML library
2. Manipulating multiple 2D arays
3. Understanding how to use multiple buffers in computer graphics
4. How to write code from scratch

## Image Processing.

In computer graphics, we'll often use this idea of sampling neighboring pixels to transform our data. We'll have more specific assignment(s) related to image processing, and the idea of using a [convolution matrix](https://en.wikipedia.org/wiki/Kernel_(image_processing)#Convolution) to determine the final output of a pixel.

## Your task

Your task is to implement a [water ripple effect (Click for example)](https://editor.p5js.org/codingtrain/sketches/tYXtzNSl). There could be quite a bit of variation on how to implement this effect, so as long as you generate something similar that is fine for the purpose of this assignment.

I think the simplest approach, would be to work with the provided code, and practice changing pixels, and then working with two buffers as demonstrated in the provided links(i.e. the GameDev.net article) in the assignment . Some folks however may prefer to work with other data structures or perhaps use lots of shapes (note: creating lots of circle shapes will likely slow your machine down quite a bit!)

### Assignment Strategy

### How to compile and run your program on Linux

The program

- Compile with: `g++ -std=c++17 -g ./src/*.cpp -I./include -o prog -lsfml-graphics -lsfml-window -lsfml-system`
- Run with: `./prog`
- Debug with gdb: `gdb ./prog --tui`

# Deliverables

- Commit a working water effect.
	- You should not commit any of the binary files

# F.A.Q. (Instructor Anticipated Questions)

- Q: How could I improve upon this effect? 
- A: Currently the ripples pass through themselves quite easily. You could model the direction (using another image that captures a directional vector) of each pixel as it moves, and determine the appropriate angle to propogate the wave when two waves collide.
- Q: Why doesn't your example look like [water ripple effect (Click for example)](https://editor.p5js.org/codingtrain/sketches/tYXtzNSl)?
- A: The dampening value for my pixels is at 0.9999 and is a double. Note however, my pixels maximum value is 255,255,255 in the RGB components respectively. In the previous link, the example uses a value of 2500, which will allow the water ripples to propogate further. Feel free to tweak your assignment as you like.
- Q: Can I create rain drops?
- A: Sure, in my implementation I do something relatively simple, and just set pixels to random values
	- Example
	- 
	```cpp 
	// Be sure to seed your random numbers ahead of time for more randomness!
	for(int i=0; i < 10; i++){
             int randx = rand() %400;
             int randy = rand() %400;
             int val = rand() %128+127;
             backbuffer.setPixel(randx,randy,sf::Color(val,val,val,32));
         }
	```

# Going Further

(Finish early? Assignment too easy? Try this optional task)

- Try creating a more realistic water ripple effect, where if the ripples crash into each other, they go in opposite directions.
- Try creating a more 'blue' water.
