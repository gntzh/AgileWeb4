import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListImpl implements TodoList{

    private List<Todo> todoList;

    public TodoListImpl() {
        File data = new File("src/conf/data");
        if(data.length() == 0){
            todoList = new ArrayList<>();
        }else {
            ObjectInputStream todoListInputStream = null;
            try {
                todoListInputStream = new ObjectInputStream(new FileInputStream(data));
                todoList = (List<Todo>) todoListInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    assert todoListInputStream != null;
                    todoListInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        todoList = new ArrayList<>();

    }

    @Override
    public Todo get(String id) {
        for (Todo temp : todoList){
            if (temp.getId().equals(id)){
                return temp;
            }
        }
        return null;
    }

    @Override
    public Todo add(Todo todo) {
        todoList.add(todo);
        save();
        return todo;
    }

    @Override
    public Todo delete(String id) {
        for (Todo temp : todoList){
            if (temp.getId().equals(id)){
                todoList.remove(temp);
                save();
                return temp;
            }
        }
        return null;
    }

    @Override
    public Todo finish(String id) {
        for (Todo temp : todoList){
            if (temp.getId().equals(id)){
                temp.setFinished(true);
                save();
                return temp;
            }
        }
        return null;
    }

    private void save() {
        ObjectOutputStream todoListOutputStream = null;
        try {
            todoListOutputStream = new ObjectOutputStream(new FileOutputStream(new File("src/conf/data")));
            todoListOutputStream.writeObject(todoList);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (todoListOutputStream != null){
                    todoListOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
