package noticeBoard;

import java.util.ArrayList;
import java.util.List;

public class MainTester {
	public static void main(String[] args) {
		NoticeBoard noticeBoard = new NoticeBoard();
		
		Notice notice1 = new Notice("Hello World!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice1);
		
		Notice notice2 = new Notice("Hello World2!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice2);
		
		Notice notice3 = new Notice("Hello World3!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice3);
		
		Notice notice4 = new Notice("Hello World4!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice4);
		
		Notice notice5 = new Notice("Hello World5!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice5);
		
		Notice notice6 = new Notice("Hello World6!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice6);
		
		Notice notice7 = new Notice("Hello World7!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice7);
		
		Notice notice8 = new Notice("Hello World8!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice8);
		
		Notice notice9 = new Notice("Hello World9!", "This is the first notice", "1938382");
		NoticeBoardPersistence.placeNotice(noticeBoard, notice9);
		
		System.out.println(noticeBoard);
		
		
		
	}
}
