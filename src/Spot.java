
public class Spot {
	Long id;
	Vehicle vehicle;
	SpotTypeEnum type;
	public Spot(Long id, SpotTypeEnum type) {
		this.id = id;
		this.type = type;
		vehicle=null;
	}
	public void clear() {
		vehicle = null;
	}
	
}
