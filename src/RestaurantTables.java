public class RestaurantTables extends DatabaseRunner {
  public RestaurantTables() {

  }

  private void create() {
    JFrame window = new JFrame("Stoner's Late Night Cravings - User Panel");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel p = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(2,10,2,10);

    window.add( p );


    JTable table = new JTable();
    String[] columnNames = {"Distance", "Name", "Genre", "Phone", "Website", "Closing Time"};
    Object[][] data;
    populateTable(data);

    p.add(table);

        data[r][i] =
  }
  private void populateTable(Object[][] data) {
    int columns = data.length();
    ResultSet rs = super.executeQuery("SELECT Latitude, Name, GenreID, Phone, Website, ClosingTime");

    try {
      int i = 0;
      int r = 0;
      while(rs.next()) {
        if(i>= columns) {
          i=0;
        }
        data[r][i] = rs.getInt("Latitude");
        i++;
        data[r][i] = rs.getString("Name");
        i++;
        data[r][i] = rs.getInt("GenreID");
        i++;
        data[r][i] = rs.getString("Phone");
        i++;
        data[r][i] = rs.getString("Website");
        i++;
        data[r][i] = rs.getInt("ClosingTime");
        i++;
      }
    } catch (SQLException as e) {
      e.close();

    }
  }

}
