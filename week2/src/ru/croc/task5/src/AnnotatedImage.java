import java.util.Objects;

class AnnotatedImage extends Annotation {

    private final String imagePath;
    private final Annotation[] annotations;
    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y){
        Annotation annotation = null;
        for (int i = 0; i < annotations.length; i++){
            if(annotations[i].figure.contains(x, y)){
                annotation = annotations[i];
                return annotation;
            }
        }
        return null;
    }
    Annotation findByLabel(String label) {
        Annotation annotation = null;
        for (Annotation value : annotations) {
            if (value.toString().contains(label)) {
                annotation = value;
                return annotation;
            }
        }
        return null;
    }

}
