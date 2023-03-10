
package model;

public class Line {
    private String item;
    private int price;
    private int count;
    private Invoice invoice;

    public Line() {
    }

    public Line(String item, int price, int count, Invoice invoice) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public double getLineTotal() {
        return price * count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Line{" + "num=" + invoice.getinvoiceNum() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    public Invoice getInvoice() {
        return invoice;
    }
    
    public String getAsCSV() {
        return invoice.getinvoiceNum() + "," + item + "," + price + "," + count;
    }
    
}
