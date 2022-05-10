package co.dodo.dungeons.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.dao.DataSource;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.vo.CardListVO;
import co.dodo.dungeons.vo.InventoryVO;
import co.dodo.dungeons.vo.PlayerVO;

public class SaveFilesImpl implements SaveFiles 
{
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<PlayerVO> playerAllSelect() 
	{
		List<PlayerVO> list = new ArrayList<PlayerVO>();
		PlayerVO vo;
		String sql = "SELECT * FROM PLAYER";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				vo = new PlayerVO();
				vo.setUserId(rs.getInt("userid"));
				vo.setPw(rs.getInt("pw"));
				vo.setProgress(rs.getInt("progress"));
				vo.setKills(rs.getInt("kills"));
				vo.setAction(rs.getInt("action"));
				vo.setAttack(rs.getInt("attack"));
				vo.setDefense(rs.getInt("defense"));
				vo.setMoney(rs.getInt("money"));
				vo.setHp(rs.getInt("hp"));
				vo.setUserName(rs.getString("username"));
				list.add(vo);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		
		return list;
	}

	@Override
	public PlayerVO playerSelect(PlayerVO vo) 
	{
		String sql = "SELECT * FROM PLAYER WHERE USERNAME = ?";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserName()); // 숫자는  ?의 위치, 그 위치에 필요한 인수 넣기.
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				vo = new PlayerVO();
				vo.setUserId(rs.getInt("userid"));
				vo.setPw(rs.getInt("pw"));
				vo.setProgress(rs.getInt("progress"));
				vo.setKills(rs.getInt("kills"));
				vo.setAction(rs.getInt("action"));
				vo.setAttack(rs.getInt("attack"));
				vo.setDefense(rs.getInt("defense"));
				vo.setMoney(rs.getInt("money"));
				vo.setHp(rs.getInt("hp"));
				vo.setUserName(rs.getString("username"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return vo;
	}

	@Override
	public int playerInsert(PlayerVO vo) 
	{
		int n = 0;
		String sql = "INSERT INTO PLAYER VALUES(USER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPw());
			psmt.setInt(2, vo.getProgress());
			psmt.setInt(3, vo.getKills());
			psmt.setInt(4, vo.getAction());
			psmt.setInt(5, vo.getAttack());
			psmt.setInt(6, vo.getDefense());
			psmt.setInt(7, vo.getMoney());
			psmt.setInt(8, vo.getHp());
			psmt.setString(9, vo.getUserName());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int playerUpdate(PlayerVO vo)
	{
		int n = 0;
		String sql = "UPDATE PLAYER SET PROGRESS = ?, KILLS=?, ACTION=?, ATTACK=?,"
				+ "DEFENSE=?, MONEY=?, HP=? WHERE USERID=? ";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getProgress());
			psmt.setInt(2, vo.getKills());
			psmt.setInt(3, vo.getAction());
			psmt.setInt(4, vo.getAttack());
			psmt.setInt(5, vo.getDefense());
			psmt.setInt(6, vo.getMoney());
			psmt.setInt(7, vo.getHp());
			psmt.setInt(8, vo.getUserId());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int playerDelete(PlayerVO vo) 
	{
		int n =0;
		String sql ="DELETE PLAYER WHERE USERID = ?";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getUserId());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int cardInsert(CardVO vo) 
	{
		int n = 0;
		String sql = "INSERT INTO CARD VALUES(CARD_SEQ.NEXTVAL,?,?,?,?,?)";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCardName());
			psmt.setInt(2, vo.getAttack());
			psmt.setInt(3, vo.getDefense());
			psmt.setInt(4, vo.getActionConsumption());
			psmt.setString(5, vo.getReadme());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int cardUpdate(CardVO vo) 
	{
		int n = 0;
		String sql = "update card set attack = ?, defense = ? where cardid = ?)";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getAttack());
			psmt.setInt(2, vo.getDefense());
			psmt.setInt(3, vo.getCardId());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int itemInsert(ItemVO vo)
	{
		int n = 0;
		String sql = "INSERT INTO ITEM VALUES(ITEM_SEQ.NEXTVAL,?,?,?,?,?,?)";
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemName());
			psmt.setInt(2, vo.getAttack());
			psmt.setInt(3, vo.getDefense());
			psmt.setInt(4, vo.getInstantDamage());
			psmt.setString(5, vo.getReadme());
			psmt.setInt(6, vo.getPrice());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}
	
	@Override
	public int inventoryInsert(ItemVO vo1,PlayerVO vo2)
	{
		int n = 0;
		String sql = "INSERT INTO INVENTORY VALUES(INVEN_SEQ.NEXTVAL,?,?)";
		try
		{
			InventoryVO vo = new InventoryVO();
			vo.setItemId(vo1.getItemId());
			vo.setUserId(vo2.getUserId());
			
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getItemId());
			psmt.setInt(2, vo.getUserId());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}
	
	@Override
	public int cardListInsert(CardVO vo1,PlayerVO vo2)
	{
		int n = 0;
		String sql = "INSERT INTO CARDLIST VALUES(CARDLIST_SEQ.NEXTVAL,?,?)";
		try
		{
			CardListVO vo = new CardListVO();
			vo.setCardId(vo1.getCardId());
			vo.setUserId(vo2.getUserId());
			
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getUserId());
			psmt.setInt(2, vo.getCardId());
			n = psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return n;
	}
	
	@Override
	public InventoryVO InventorySelect(PlayerVO vo) // 인벤토리 불러오기
	{
		String sql = "SELECT * FROM INVENTORY WHERE USERID = ?";
		InventoryVO vo1 = null;
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getUserId()); // 숫자는  ?의 위치, 그 위치에 필요한 인수 넣기.
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				vo1 = new InventoryVO();
				vo1.setInvenId(rs.getInt("invenid"));
				vo1.setItemId(rs.getInt("itemid"));
				vo1.setUserId(rs.getInt("userid"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return vo1;
	}
	
	@Override
	public List<CardVO> CardListSelect(PlayerVO vo1) // 플레이어가 가진 카드리스트를 가져옴.
	{
		List<CardVO> list = new ArrayList<CardVO>();
		String sql = "select a.cardid, b.cardname, b.attack, b.defense, b.actionconsumption,b.readme from cardlist a join card b on(a.cardid=b.cardid) where userid = ?";
		CardVO vo = null;
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo1.getUserId()); // 숫자는  ?의 위치, 그 위치에 필요한 인수 넣기.
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				vo = new CardVO();
				vo.setCardId(rs.getInt("cardid"));
				vo.setCardName(rs.getString("cardname"));
				vo.setAttack(rs.getInt("attack"));
				vo.setDefense(rs.getInt("Defense"));
				vo.setActionConsumption(rs.getInt("actionConsumption"));
				vo.setReadme(rs.getString("readme"));
				list.add(vo);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return list;
	}
	
	@Override
	public List<CardVO> Card5Select()
	{
		List<CardVO> list = new ArrayList<CardVO>();
		CardVO vo;
		String sql = "SELECT * FROM (SELECT * FROM card ORDER BY cardid DESC) WHERE ROWNUM <= 5"; // 최근만든 5개 초기카드 불러오기.
		try
		{
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				vo = new CardVO();
				vo.setCardId(rs.getInt("cardid"));
				vo.setCardName(rs.getString("cardname"));
				vo.setAttack(rs.getInt("attack"));
				vo.setDefense(rs.getInt("Defense"));
				vo.setActionConsumption(rs.getInt("actionConsumption"));
				vo.setReadme(rs.getString("readme"));
				list.add(vo);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		
		return list;
	}
	
	private void close() // 커넥션 닫기 메서드
	{
		try 
		{
			if(rs != null)
			{
				rs.close();
			}
			if(psmt != null)
			{
				psmt.close();
			}
			if(conn != null) 
			{
				conn.close();
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
