package DataTableTest.MainPac;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "item_test")
class ItemTest {
	@Id
	@GeneratedValue
	@Column (name = "id")
    private int id;
	 @Column (name = "result", nullable = false)
    private boolean result;
	 @Column (name = "result_text", nullable = false)
    private String resultText;


    public ItemTest() {

    }

    public ItemTest(boolean result, String resultText) {
        this.result = result;
        this.resultText = resultText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResultText(String result_text) {
        resultText = result_text;
    }

    public String getResultText() {
        return resultText;
    }
}