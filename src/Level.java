import java.util.ArrayList;

public class Level {
	Long id;
	ArrayList<Row> rows;
	public Level(Long id) {
		this.id = id;
		rows = new ArrayList<>();
	}
	public void addRow(Row row) {
		rows.add(row);
	}
}
