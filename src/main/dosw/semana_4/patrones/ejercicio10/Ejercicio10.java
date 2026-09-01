package dosw.semana_4.patrones.ejercicio10;

/**
 * Ejercicio 10 - Aplicacion de Edicion de Imagenes
 * Patrones: Decorator + Command
 */
public class Ejercicio10 {

    interface Image { String render(); }
    static class BaseImage implements Image {
        public String render() { return "ImagenBase"; }
    }
    static abstract class ImageDecorator implements Image {
        protected final Image wrapped;
        ImageDecorator(Image wrapped) { this.wrapped = wrapped; }
    }
    static class GrayscaleDecorator extends ImageDecorator {
        GrayscaleDecorator(Image i) { super(i); }
        public String render() { return wrapped.render() + " + BlancoYNegro"; }
    }
    static class SepiaDecorator extends ImageDecorator {
        SepiaDecorator(Image i) { super(i); }
        public String render() { return wrapped.render() + " + Sepia"; }
    }
    static class BrightnessDecorator extends ImageDecorator {
        BrightnessDecorator(Image i) { super(i); }
        public String render() { return wrapped.render() + " + Brillo"; }
    }

    interface ImageCommand { void execute(); void undo(); }

    static class ImageEditor {
        Image image;
        ImageEditor(Image base) { this.image = base; }
    }

    static class ApplyFilterCommand implements ImageCommand {
        private final ImageEditor editor;
        private final java.util.function.Function<Image, Image> filter;
        private Image previous;
        ApplyFilterCommand(ImageEditor editor, java.util.function.Function<Image, Image> filter) {
            this.editor = editor; this.filter = filter;
        }
        public void execute() {
            previous = editor.image;
            editor.image = filter.apply(editor.image);
            System.out.println("Aplicado -> " + editor.image.render());
        }
        public void undo() {
            editor.image = previous;
            System.out.println("Deshecho -> " + editor.image.render());
        }
    }

    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor(new BaseImage());

        ImageCommand sepia = new ApplyFilterCommand(editor, SepiaDecorator::new);
        sepia.execute();

        ImageCommand brillo = new ApplyFilterCommand(editor, BrightnessDecorator::new);
        brillo.execute();

        ImageCommand grayscale = new ApplyFilterCommand(editor, GrayscaleDecorator::new);
        grayscale.execute();

        System.out.println("--- Deshaciendo el ultimo filtro aplicado (grayscale) ---");
        grayscale.undo();
    }
}
