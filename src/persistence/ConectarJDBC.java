
package persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Player;
import model.Team;
import model.Ranking;

/*
 * @author david
 */

public class ConectarJDBC {
    private Connection conexio;
    
    public ConectarJDBC() {
    
    }
    
    //1
    public void insertarTeam(Team t1) throws SQLException {
        String insert = "insert into team values (?,?,?);";
        try (PreparedStatement ps = conexio.prepareStatement(insert)) {
            ps.setString(1, t1.getName());
            ps.setString(2, t1.getCity());
            ps.setDate(3, java.sql.Date.valueOf(t1.getCreacion()));
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //2
    public void insertarPlayer(Player p1) throws SQLException {
        String insert = "insert into player values (?,?,?,?,?,?,?);";
        try (PreparedStatement ps = conexio.prepareStatement(insert)) {
            ps.setString(1, p1.getName());
            ps.setDate(2, java.sql.Date.valueOf(p1.getBirth()));
            ps.setInt(3, p1.getNbaskets());
            ps.setInt(4, p1.getNassists());
            ps.setInt(5, p1.getNrebounds());
            ps.setString(6, p1.getPosition());
            ps.setString(7, p1.getTeam().getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //3
    public void modificarPlayer1(Player mp1) throws SQLException {
        String update = "update player set nbaskets = ? , nassists = ?, nrebounds = ? where name = ? ";
        try (PreparedStatement ps = conexio.prepareStatement(update)) {
            ps.setString(1, mp1.getName());
            ps.setInt(3, mp1.getNbaskets());
            ps.setInt(4, mp1.getNassists());
            ps.setInt(5, mp1.getNrebounds());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //4
    public void modificarPlayer2(Player mp2) throws SQLException {
        String update = "update player set team = ? where name = ? ";
        try (PreparedStatement ps = conexio.prepareStatement(update)) {
            ps.setString(1, mp2.getName());
            ps.setString(7, mp2.getTeam().getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //5
    public void borrarPlayer(Player bt) throws SQLException {
        String delete = "delete where name = ? ";
        try (PreparedStatement ps = conexio.prepareStatement(delete)) {
            ps.setString(1, bt.getName());
            ps.setDate(2, java.sql.Date.valueOf(bt.getBirth()));
            ps.setInt(3, bt.getNbaskets());
            ps.setInt(4, bt.getNassists());
            ps.setInt(5, bt.getNrebounds());
            ps.setString(6, bt.getPosition());
            ps.setString(7, bt.getTeam().getName());
            ps.executeUpdate();
            ps.close();
        }
    }
    
    //6
    public Player selectPlayerbyName(String name) throws SQLException {
        Player p = new Player();
        String query = "select * from player where name = ?";
        PreparedStatement ps = conexio.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            p.setName("name");
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setNassists(rs.getInt("nassists"));
            p.setNrebounds(rs.getInt("nrebounds"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
        }
        rs.close();
        ps.close();
        return p;
    }
    
    //7
    public List<Player> selectAllPlayers(String name) throws SQLException {
        String query = "select * from player where name like '%"+name+"%' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setNassists(rs.getInt("nassists"));
            p.setNrebounds(rs.getInt("nrebounds"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
        }
        rs.close();
        st.close();
        return players;
    }
    
    //8
    public List<Player> selectPlayerGreaterThan(int nbaskets) throws SQLException {
        String query = "select * from player where nbaskets >= =? ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("Nombre"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
        }
        rs.close();
        st.close();
        return players;
    }
    
    //9
    public List<Player> selectPlayerAssistsBetween() throws SQLException {
        String query = "select * from player where nassists between =? and =?";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setNbaskets(rs.getInt("nbaskets"));
            p.setNassists(rs.getInt("nassists"));
        }
        rs.close();
        st.close();
        return players;
    }

    //10
    public List<Player> selectPlayerByPosition(String position) throws SQLException {
        String query = "select * from player where position like '%"+position+"%'";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
            p.setPosition(rs.getString("position"));
        }
        rs.close();
        st.close();
        return players;
    }
    
    //11
    public List<Player> selectPlayerByBirth(LocalDate birth) throws SQLException {
        String query = "select * from player where birth <= =?";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setBirth(rs.getDate("birth").toLocalDate());
        }
        rs.close();
        st.close();
        return players;
    }
    
    //12
    public List<Player> selectPlayerByGroupBy(String position) throws SQLException {
        String query = "select name, AVG(nbaskets), AVG(nassists), AVG(nrebounds)+"
                + "MAX(nbaskets) + MAX(nassists) + MAX(nrebounds)+"
                + "MIN(nbaskets) + MIN(nassists) + MIN(nrebounds)+"
                + "from player where position like '%"+position+"%'";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setTeam(new Team(rs.getString("team")));
            //revisar, resto de campos
        }
        rs.close();
        st.close();
        return players;
    }
    
    //13
    public List<Player> selectPlayerByBaskets() throws SQLException {
        String query = "select name, nbaskets from player order by nbaskets desc ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setNbaskets(rs.getInt("nbaskets"));
        }
        rs.close();
        st.close();
        return players;
    }
    
    //14
    public List<Ranking> selectPlayerByBasketsRanking() throws SQLException {
        String query = "set @num=0; select @num=0:=@num+1 AS 'Ranking' name, nbaskets from player order by nbaskets desc ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Ranking> rankings = new ArrayList<>();
        while (rs.next()) {
            Ranking r = new Ranking ();
            r.setRanking(rs.getInt("ranking"));
            r.setName(rs.getString("name"));
            r.setNbaskets(rs.getInt("nbaskets"));
            rankings.add(r);
        }
        rs.close();
        st.close();
        return rankings;
    }
    
    //15
    public List<Team> selectTeamByCity(String city) throws SQLException {
        String query = "select * from team where city = '%+city+%' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team t = new Team();
            t.setCity("team");
            t.setName("city");
            t.setCreacion(rs.getDate("creacion").toLocalDate());
        }
        rs.close();
        st.close();
        return teams;
    }
    
    //16
    public List<Player> selectPlayerTeam(String team) throws SQLException {
        String query = "select * from player where team='"+team+"' ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
        }
        rs.close();
        st.close();
        return players;
    }

    //17
    public List<Player> selectPlayerByTeamAndPosition(String position, String team) throws SQLException {
        String query = "select name from player where position='%"+position+"%' and team='%"+team+"% ";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setPosition(rs.getString("position"));
            p.setTeam(new Team(rs.getString("team")));
            //revisar
        }
        rs.close();
        st.close();
        return players;
    }
      
    //18
    public List<Player> selectPlayerMaxNbaskets() throws SQLException {
        String query = "select name, MAX(nbaskets) from player";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("name"));
            p.setNbaskets(rs.getInt("nbaskets"));
            //revisar
        }
        rs.close();
        st.close();
        return players;
    }

    public void conectar() throws SQLException {
        String url="jdbc:mysql://localhost:3306/basket";
        String usr="root";
        String pass="d4v1d001";
        conexio = (Connection) DriverManager.getConnection(url, usr, pass);
    }
    
    public void desconectar() throws SQLException {
            if (conexio != null) {
            conexio.close();
        }
    } 

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}