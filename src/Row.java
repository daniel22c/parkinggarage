import java.util.ArrayList;

public class Row {
	Long id;
	ArrayList<Spot> spots;
	public Row(Long id) {
		this.id= id;
		spots = new ArrayList<>();
	}
	public void addSpot(Spot spot) {
		spots.add(spot);
	}
}
