public class Main {
    public static void main(String[] args) {
        ConnectFourModel model = new ConnectFourModel();
        ConnectFourView view = new ConnectFourView(model);
        model.addConnectFourView(view);
    }
}