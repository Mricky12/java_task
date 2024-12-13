package task.dto;

public class TaskDTO {

	//	フィールド定義
	private int taskId; //タスクID
	private String taskTitle; //タスクタイトル
	private String task; //タスク内容
	private byte[] taskImage; //タスク画像
	private int userId; //ユーザーID
	private Integer colorId; //カラーID（NULL許容なのでInteger）
	private boolean trash; //ゴミ箱フラグ

	// コンストラクタ
	public TaskDTO() {
	}

	// 全フィールドを因数に持つコンストラクタ
	public TaskDTO(int taskId, String taskTitle, String task, byte[] taskImage, int userID, Integer colorId,
			boolean trash, int userId) {
		this.taskId = taskId;
		this.taskTitle = taskTitle;
		this.task = task;
		this.taskImage = taskImage;
		this.userId = userId;
		this.colorId = colorId;
		this.trash = trash;
	}

	//Getterメソッド
	public int getTaskId() {
		return taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public String getTask() {
		return task;
	}

	public byte[] getTaskImage() {
		return taskImage;
	}

	public int getUserId() {
		return userId;
	}

	public Integer getColorId() {
		return colorId;
	}

	public boolean isTrash() {
		return trash;
	}

	//	Setterメソッド
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void settaskImage(byte[] taskImage) {
		this.taskImage = taskImage;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

}