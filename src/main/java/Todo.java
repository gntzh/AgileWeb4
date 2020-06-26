import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Todo implements Serializable {
    private String name;
    private String detail;
    private Boolean finished;
    private String id;

    public Todo(String name, Boolean finished) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.finished = finished;
    }

    public Todo(String name, String detail) {
        this.name = name;
        this.detail = detail;
        this.id = UUID.randomUUID().toString();
    }

    public Todo(String name, String detail, Boolean finished) {
        this.name = name;
        this.detail = detail;
        this.finished = finished;
        this.id = UUID.randomUUID().toString();
    }

    public Todo(String name, Boolean finished, String id) {
        this.name = name;
        this.finished = finished;
        this.id = id;
    }

    public Todo(String name, Boolean finished, String detail, String id) {
        this.name = name;
        this.detail = detail;
        this.finished = finished;
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", finished=" + finished +
                '}';
    }
}
