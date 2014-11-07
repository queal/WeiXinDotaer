package per.weixin.pojo;

import java.util.List;

public class Articles {
	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Articles [item=" + item + "]";
	}

}