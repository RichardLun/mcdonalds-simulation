class CustomerVIP {
    public int order;
    private int enter;

    public CustomerVIP() {
        order = (int) (Math.random() * 5 + 2);
    }

    public int getOrder() {
        return order;
    }

    public void orderDown() {
        order--;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int x) {
        enter = x;
    }

    public String toString() {
        return enter + "";
    }
}