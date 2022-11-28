#include <Fire.hpp>


// Fire.cpp

// TODO: Your implementation of Fire.cpp here

// Initialize several structs of color
// Colors interpolated using this tool: https://meyerweb.com/eric/tools/color-blend/#538DD6:C5D9F1:1:rgbd
static RGBA g_firePallete[] = {
    {83, 141, 214, 255}, // White is the hottest
    {140, 179, 228, 255},
    {197, 217, 241, 255},
    {226, 236, 248, 255},
    {255, 255, 255, 255},
    {255, 255, 229, 255},
    {255, 255, 202, 255},
    {255, 255, 102, 255},
    {254, 255, 1, 255},
    {255, 224, 1, 255},
    {255, 192, 0, 255},
    {254, 96, 1, 255},
    {253, 0, 2, 255},
    {223, 96, 97, 255},
    {192, 192, 192, 255},
    {128, 128, 128, 255},
    {64, 64, 64, 255},
    {32, 32, 32, 255},
    {0, 0, 0, 255} // Black is the darkest
};

Fire::Fire(unsigned int width, unsigned int height) {
    m_width = width;
    m_height = height;
    m_firePixels = new int[m_width * m_height]; 

    for (int i = 0; i < m_width * m_height; i++) {
        m_firePixels[i] = 19;
    }

    for (int i = 0; i < m_width; i++) {
        m_firePixels[(m_height - 1) * m_width + i] = 0;
    }
}


// Default Destructor which will free memory
Fire::~Fire()
{
    delete[] m_firePixels;
}

void Fire::SpreadFire(int from)
{
    int pixel = m_firePixels[from];
    if (pixel == 19) {
        m_firePixels[from - m_width] = 19;
    } 
    else {
        int r = rand() % 3; 
        int to = from + r + 1; 
        m_firePixels[to - m_width] = pixel + r; 
    }
   
}

void Fire::DoFire()
{
    for (int x = 0; x < m_width; x++) {
        for (int y = 1; y < m_height; y++) {
            SpreadFire(y * m_width + x);
        }
    }
}

void Fire::Render(sf::RenderWindow &window)
{
    DoFire();
    sf::Image fire;
    sf::Texture texture;
    sf::Sprite sprite;
    sf::Uint8* pixels;

    pixels = new sf::Uint8[m_height * m_width * 4];

    for (int x = 0; x < m_height; x++)
    {
        for (int y = 0; y < m_width; y++) {
        RGBA color = g_firePallete[m_firePixels[x * m_width + y]];
        pixels[(x * m_width + y) * 4] = (sf::Uint8)color.r;
        pixels[(x * m_width + y) * 4 + 1] = (sf::Uint8)color.g;
        pixels[(x * m_width + y) * 4 + 2] = (sf::Uint8)color.b;
        pixels[(x * m_width + y) * 4 + 3] = (sf::Uint8)color.a;
    }
    } 

    fire.create(m_width, m_height, pixels);   
    texture.loadFromImage(fire);
    sprite.setTexture(texture);
    window.draw(sprite);
}

