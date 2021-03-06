package co.dodo.dungeons.vo;

import lombok.Data;

@Data
public class PlayerVO 
{
	
	public PlayerVO() {}
	
	public PlayerVO(String userName, int pw) 
	{
		this.userName = userName;
		this.pw = pw;
	}
	
	public PlayerVO(String userName,int pw, int progress, int kills, int action, int attack, int defense, int money, int hp) 
	{
		this.userName = userName;
		this.pw = pw;
		this.progress = progress;
		this.kills = kills;
		this.action = action;
		this.attack = attack;
		this.defense = defense;
		this.money = money;
		this.hp = hp;
	}
	
	private int userId; // 아이디 고유넘버 pk
	private String userName; // 아이디 이름
	private int pw; // 비번
	private int progress = 0; // 진행상황
	private int kills = 0; //킬수
	private int action = 3; // 행동력
	private int attack = 15;
	private int defense = 5;
	private int money = 0;
	private int hp = 100;
	
	@Override
	public String toString()
	{
		System.out.println("== 캐릭터 정보 ==");
		System.out.println("이름   : "+userName);
		System.out.println("hp     : "+hp);
		System.out.println("공격   : "+attack);
		System.out.println("방어   : "+defense);
		System.out.println("소지금 : "+money);
		System.out.println();
		return null;
	}
	
	public void rank()
	{
		System.out.print("이름   : "+userName+"  ");
		System.out.print("포인트 : "+kills+"  ");
		System.out.print("소지금 : "+money+"  ");
		System.out.println("돌파한 방의 개수 : "+progress);
	}
}
