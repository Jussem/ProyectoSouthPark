package autonoma.deadlycode.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenedor de objetos Sprite que implementa la interfaz {@link GraphicContainer}.
 * Permite agregar, remover y acceder a una lista de sprites.
 * 
  * @author Juan Sebastian Lopez Guzman, Cristian Camilo Salazar, Juan Jose Morales
 * @version 1.0
 * @since 2025-05-19
 */
public class SpriteContainer implements GraphicContainer {
    
    /**
     * Lista que almacena los sprites contenidos en este contenedor.
     */
    private List<Sprite> sprites;
    
    /**
     * Crea una nueva instancia de SpriteContainer con una lista vacía de sprites.
     *
     * @since 1.0
     */
    public SpriteContainer() {
        this.sprites = new ArrayList<>();
    }
    
    /**
     * Agrega un sprite al contenedor si no es nulo.
     * Implementación del método definido en {@link GraphicContainer}.
     *
     * @param sprite El sprite a agregar al contenedor.
     * @since 1.0
     */
    @Override
    public void agregarSprite(Sprite sprite) {
        if (sprite != null) {
            sprites.add(sprite);
        }
    }
    
    /**
     * Remueve un sprite específico del contenedor.
     *
     * @param sprite El sprite a remover.
     * @return {@code true} si el sprite estaba presente y fue removido, {@code false} en caso contrario.
     * @since 1.0
     */
    public boolean removerSprite(Sprite sprite) {
        return sprites.remove(sprite);
    }
    
    /**
     * Devuelve la lista completa de sprites contenidos en este contenedor.
     *
     * @return Una lista de objetos {@link Sprite}.
     * @since 1.0
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}
