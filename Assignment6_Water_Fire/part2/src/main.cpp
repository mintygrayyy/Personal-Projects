#include <SFML/Graphics.hpp>

#include <iostream>
#include <cstring>
#include <cstdlib>

bool mouseInBounds(const sf::RenderWindow &renderWindow)
{
    sf::Vector2i mouse = sf::Mouse::getPosition(renderWindow);
    if (mouse.x > -1 && mouse.x < renderWindow.getSize().x - 1)
    {
        if (mouse.y > -1 && mouse.y < renderWindow.getSize().y - 1)
        {
            return true;
        }
    }
    return false;
}

int main()
{

    // In order for this trick to work, we're going to need
    // two buffers.
    //
    // The 'front buffer' and the 'back buffer'
    // The front buffer is what we display and the backbuffer
    //
    sf::Image backbuffer;
    sf::Image frontbuffer;

    // Create an array of pixels to populate our initial pixels.
    const sf::Uint8 *pixels = new sf::Uint8[400 * 400 * 4];
    std::memset((void *)pixels, 0, 400 * 400 * 4);

    backbuffer.create(400, 400, pixels);
    frontbuffer.create(400, 400, pixels);

    // Main render window
    sf::RenderWindow renderWindow(sf::VideoMode(400, 400), "Water example");
    // Attempt to cap the frame rate of the window
    renderWindow.setFramerateLimit(60);



    // Main Application Loop
    while (renderWindow.isOpen())
    {
        sf::Event event;
        while (renderWindow.pollEvent(event))
        {
            if (event.type == sf::Event::Closed)
            {
                renderWindow.close();
            }
        }

        // Implement the water routine here
        // TODO
        //Set pixel as the average of colors from neighboring pixels (sampling)
        
        float damping = 0.99f;
    
        for (int x = 1; x < 400 - 1; x++)
        {
            for (int y = 1; y < 400 - 1; y++)
            {
                sf::Color neighbor1 = backbuffer.getPixel(x + 1, y);
                sf::Color neighbor2 = backbuffer.getPixel(x - 1, y);
                sf::Color neighbor3 = backbuffer.getPixel(x, y+1);
                sf::Color neighbor4 = backbuffer.getPixel(x, y-1); 

                sf::Color pixelColor = frontbuffer.getPixel(x, y);

                float averagered = ((neighbor1.r + neighbor2.r + neighbor3.r + neighbor4.r)/2) - pixelColor.r;
                float averagegreen = ((neighbor1.g + neighbor2.g + neighbor3.g + neighbor4.g)/2) - pixelColor.g;
                float averageblue = ((neighbor1.b + neighbor2.b + neighbor3.b + neighbor4.b)/2) - pixelColor.b;
                
                float high = 255;
                float low = 0;
                float red = std::clamp(averagered, low, high);
                float blue = std::clamp(averageblue, low, high);
                float green = std::clamp(averagegreen, low, high);

                sf::Uint8 averagered1 = red * damping;
                sf::Uint8 averagegreen1 = green * damping;
                sf::Uint8 averageblue1 = blue * damping; 

                frontbuffer.setPixel(x, y, sf::Color(averagered1, averagegreen1,averageblue1));
             }
        }    

        
        // If the mouse is pressed, create a wave
        if (sf::Mouse::isButtonPressed(sf::Mouse::Left))
        {
            if (mouseInBounds(renderWindow))
            {
                int x;
                int y;
                sf::Vector2i mouse = sf::Mouse::getPosition(renderWindow);
                frontbuffer.setPixel(mouse.x, mouse.y, sf::Color(255, 255, 255));
                
            }
        }

         // ======== Swap the two buffers ============
        // TODO: swap front and back buffers, note that you may need
        // 	 an additional temporary buffer to perform the swap.
        //       OR even better, look at std::swap(... , ...); in Modern C++
        std::swap(frontbuffer, backbuffer); 


    // Create a Texture for the background
    sf::Texture backgroundTexture;
    // Load into the backgroundTexture our frontbuffer
    // The frontbuffer is where we draw
    backgroundTexture.loadFromImage(frontbuffer);

    // Create a sprite
    sf::Sprite sprite;
    sprite.setTexture(backgroundTexture);


        // ======== Draw our scene =============
        // Clear the window
        renderWindow.clear();
        // Draw the sprite
        // Keep in mind, the sprite holds our 'backgroundTexture'
        renderWindow.draw(sprite);
        // Display the contents drawn to the renderWindow
        renderWindow.display();
    }

    return 0;
}

