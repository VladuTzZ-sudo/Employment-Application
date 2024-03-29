package com.TEMA;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TreeSet;

public class GUIInterface extends JFrame {
    private JPanel HomePage;
    private JPanel ManagerPage;
    private JPanel ProfilePage;
    private JButton adminPageButton;
    private JButton managerPageButton;
    private JButton profilePageButton;
    private JPanel LeftCompanyPanel;
    private JButton backButton2;
    private JPanel MainPage;
    private JButton ButtonSearchUsersProfile;
    private JTextField CautareUsers;
    private JPanel TabelPanel;
    private JScrollPane ScrollTabelPanel;
    private JSplitPane ManagerPane;
    private JButton ManagerBackButton;
    private JPanel UserOrCompany;
    private JButton BackFromUserOrCompany;
    private JButton UserChoosed;
    private JButton CompaniesChoosed;
    private JSplitPane SplitFromUserOrCompany;
    private JPanel UserChoosedPage;
    private JButton backfromUserChoosedPage;
    private JSplitPane SplitUsersAdmin;
    private JPanel LogIn;
    private JButton LogUserButton;
    private JButton LogAdminButton;
    private JButton LogManagerButton;
    private JSplitPane SplitLogin;
    private JSplitPane NotificationSplit;
    private JButton BackfromNotification;
    private JScrollPane NotificScroll;
    private JPanel NotificationPanel;
    private JButton Logout;
    private String companyName = null;
    private JPanel Requests;
    private JPanel UserRequest;
    private JPanel JobRequest;
    private JTabbedPane jTabbedPane;
    private JButton AcceptButton;
    private JButton CancelButton;
    private boolean resizable = false;
    private JButtonEdited AccesedRequest;
    private User userfromList;
    private JPanel UserInfo;
    private JSplitPane splitPaneCompany;
    private final ArrayList<JButtonCompany> jButtonCompanies = new ArrayList<>();
    private JTabbedPane jTabbedPaneCompany;
    private final JPanel EmployeesPanel = new JPanel();
    private final JPanel JobsPanel = new JPanel();
    private final JPanel EmployeeInfo = new JPanel();
    private final JPanel newEmployee = new JPanel();
    private final JScrollPane newEmployeeScroll = new JScrollPane(newEmployee);
    private final JScrollPane EmployeesScroll = new JScrollPane(EmployeesPanel);
    private final JScrollPane JobsScroll = new JScrollPane(JobsPanel);
    private final JScrollPane EmplScroll = new JScrollPane(EmployeeInfo);
    private JTextField TextAdminMove;
    private JTextField Text2AdminMove;
    private JButton ButtonAdminMove;
    private Employee SelectedEmployee;
    private JTextField SalaryText;
    private JButton SalaryCalculate;
    private Departament departamentSelected;
    private JButtonDepartment jButtonDepartmentForAddEmployee;
    private final JPanel RecruitersPanel = new JPanel();
    private final JScrollPane RecrutersScrollPane = new JScrollPane(RecruitersPanel);
    private final JPanel UserLogin = new JPanel();
    private final JPanel ManagerLogin = new JPanel();
    private final JPanel AdminLogin = new JPanel();
    private JPanel UserMainPage;
    private LoginPanel Login;
    boolean intrat, intrat2;
    private User userLog;
    private Employee employeeLog;
    private Manager Manager;
    private int ManagerType = 0;

    public GUIInterface(String titlu) {
        super(titlu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        adminPageButton.setPreferredSize(new Dimension(300, 150));
        managerPageButton.setPreferredSize(new Dimension(300, 150));
        profilePageButton.setPreferredSize(new Dimension(300, 150));

        profilePageButton.setFocusable(false);
        adminPageButton.setFocusable(false);
        managerPageButton.setFocusable(false);
        BackFromUserOrCompany.setFocusable(false);

        //Pagina de deschidere va contine un SplitPane (= SplitLogin);
        //mainLogin este container-ul initial, gol de sub cele trei butoane de Login.
        JPanel mainLogin = new JPanel();
        mainLogin.setBackground(new Color(27, 70, 75));
        UserLogin.setBackground(new Color(27, 70, 75));
        AdminLogin.setBackground(new Color(27, 70, 75));
        ManagerLogin.setBackground(new Color(27, 70, 75));
        HomePage.setBackground(new Color(27, 70, 75));

        SplitLogin.setEnabled(false);
        SplitLogin.setDividerLocation(51);
        SplitLogin.setRightComponent(mainLogin);
        mainLogin.setPreferredSize(new Dimension(SplitLogin.getRightComponent().getWidth(), SplitLogin.getRightComponent().getHeight()));

        //mainLogin - va fi inlocuit de alte 3 panel-uri corespunzatoare. Acestea vor lua locul mainLogin-ului cand butonul User, admin sau Manager login va fi apasat.
        LogUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SplitLogin.setRightComponent(UserLogin);
                UserLogin.setPreferredSize(new Dimension(SplitLogin.getRightComponent().getWidth(), SplitLogin.getRightComponent().getHeight()));
                UserLoginCreatePanel();
            }
        });

        LogAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginCreatePanel();
                SplitLogin.setRightComponent(AdminLogin);
            }
        });

        LogManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SplitLogin.setRightComponent(ManagerLogin);
                ManagerLogin.setPreferredSize(new Dimension(SplitLogin.getRightComponent().getWidth(), SplitLogin.getRightComponent().getHeight()));
                ManagerLoginCreatePanel();
            }
        });

        profilePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(ProfilePage);
                setVisible(true);
                pack();
            }
        });

        adminPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserOrCompanyPageCreation();
                setContentPane(UserOrCompany);
                setVisible(true);
                pack();
            }
        });

        managerPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerPageCreation();
                setContentPane(ManagerPage);
                setVisible(true);
                pack();
            }
        });

        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(HomePage);
            }
        });

        CautareUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CautareUsers.setText("");
            }
        });

        CautareUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CautareUsers.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                CautareUsers.setText("");
            }
        });

        CautareUsers.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ButtonSearchAction();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ButtonSearchAction();
                }
            }
        });

        ManagerBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(HomePage);
                setVisible(true);
                pack();
            }
        });

        BackFromUserOrCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(HomePage);
                setVisible(true);
                pack();
            }
        });

        UserChoosed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserChoosedPageCreation();
            }
        });

        CompaniesChoosed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyChoosedPageCreation();
            }
        });

        backfromUserChoosedPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(UserOrCompany);
                setVisible(true);
                pack();
            }
        });

        ButtonSearchUsersProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonSearchAction();
            }
        });


        BackfromNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(UserMainPage);
                UserMainPage.setVisible(true);
                pack();
            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(SplitLogin);
                SplitLogin.setVisible(true);
                pack();
            }
        });

        this.setContentPane(SplitLogin);
        setVisible(true);
        pack();
    }

    public void AdminLoginCreatePanel() {
        //Pagina creeaza panel-ul corespunzator AdminLogin-ului.
        //Va exista un singur cont de Admin = (admin, admin).
        AdminLogin.removeAll();
        LoginPanel Login = new LoginPanel("Admin", "admin", "admin");
        Login.setPreferredSize(new Dimension(525, 200));
        Login.setVisible(true);

        AdminLogin.setLayout(new GridBagLayout());
        AdminLogin.add(Login);

        Login.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Login.username.getText().compareTo("admin") == 0 && Login.password.getText().compareTo("admin") == 0) {
                    setContentPane(HomePage);
                    adminPageButton.setEnabled(true);
                    HomePage.setVisible(true);
                    ManagerType = 2;
                    pack();
                } else {
                    Login.username.setText("");
                    Login.password.setText("");
                    JFrame f = new JFrame();
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JDialog d = new JDialog(f, "Input data are wrong", true);
                    d.setLayout(new FlowLayout());
                    d.add(new JLabel("Username or password are wrong."));
                    d.setSize(500, 150);
                    d.setVisible(true);
                    pack();
                }
            }
        });
        AdminLogin.setVisible(true);
        pack();
    }

    public void ManagerLoginCreatePanel() {
        //Fiecare manager va putea sa se conecteze doar cu (numecompanie, numecompanie).
        //Iar in pagina sa, nu va putea vedea decat detaliile companiei sale, daca va incerca sa vada informatiile altei companii va primi un avertisment.
        ManagerLogin.removeAll();
        LoginPanel Login = new LoginPanel("Manager", "Google", "Google");
        Login.setPreferredSize(new Dimension(525, 200));
        Login.setVisible(true);

        ManagerLogin.setLayout(new GridBagLayout());
        ManagerLogin.add(Login);

        Login.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerType = 0;
                for (Company company : Application.getInstance().Companies) {
                    if (Login.username.getText().compareTo(company.name) == 0 && Login.password.getText().compareTo(company.name) == 0) {
                        adminPageButton.setEnabled(false);
                        setContentPane(HomePage);
                        HomePage.setVisible(true);
                        pack();
                        Manager = company.manager;
                        ManagerType = 1;
                    }
                }
                if (ManagerType != 1){
                        //Pagina de manager se poate accesa si dintr-un cont de admin, de aceea folosesc aceasta variabile ManagerType pentru a sti cine acceseaza pagina Manager.
                        //ManagerType = 0, logare esuata. ManagerType = 1 inseamna ca un manager acceseaza pagina, iar ManagerType = 2 inseamna ca adminul realizeaza actiunea.
                        Login.username.setText("");
                        Login.password.setText("");
                        JFrame f = new JFrame();
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JDialog d = new JDialog(f, "Input data are wrong", true);
                        d.setLayout(new FlowLayout());
                        d.add(new JLabel("Username or password are wrong."));
                        d.setSize(500, 150);
                        d.setVisible(true);
                        pack();
                }
            }
        });
        ManagerLogin.setVisible(true);
        pack();
    }

    public void UserLoginCreatePanel() {
        UserLogin.removeAll();
        Login = new LoginPanel("User", "dedmund@gmail.com", "male");
        Login.setPreferredSize(new Dimension(525, 200));
        Login.setVisible(true);

        UserLogin.setLayout(new GridBagLayout());
        UserLogin.add(Login);

        Login.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Orice user se va putea loga doar cu email-ul ( = username ), iar ca parola ( = campul destinat sex-ului , ma gandeam ca ar putea fi mai usor pentru testare).
                //Aici, la user = se va considera ca se poate loga un USER, un EMPLOYEE si un RECRUITER ( cei trei avand acces doar la pagina de profil a prietenilor sai, puteand sa-si vada prietenii si
                // notificarile primite. )
                intrat = false;
                for (User user : Application.getInstance().Users) {
                    if (Login.username.getText().compareTo(user.cv.information.getEmail()) == 0
                            && Login.password.getText().compareTo(user.cv.information.getSex()) == 0) {
                        userLog = user;
                        UserMainPageCreation();
                        setContentPane(UserMainPage);
                        UserMainPage.setVisible(true);
                        pack();
                        intrat = true;
                        break;
                    }
                }
                if (!intrat) {
                    intrat2 = false;
                    for (Company company : Application.getInstance().Companies) {
                        for (Departament departament : company.departaments) {
                            for (Employee employee : departament.getEmployees()) {
                                if (Login.username.getText().compareTo(employee.cv.information.getEmail()) == 0
                                        && Login.password.getText().compareTo(employee.cv.information.getSex()) == 0) {
                                    employeeLog = employee;
                                    UserMainPageCreation();
                                    setContentPane(UserMainPage);
                                    UserMainPage.setVisible(true);
                                    pack();
                                    intrat2 = true;
                                    break;
                                }
                            }
                        }
                        if (intrat2) {
                            break;
                        }
                    }
                    if (!intrat2) {
                        Login.username.setText("");
                        Login.password.setText("");
                        JFrame f = new JFrame();
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JDialog d = new JDialog(f, "Input data are wrong", true);
                        d.setLayout(new FlowLayout());
                        d.add(new JLabel("Username or password are wrong."));
                        d.setSize(500, 150);
                        d.setVisible(true);
                        pack();
                    }
                }
            }
        });
        UserLogin.setVisible(true);
        pack();
    }

    public void UserMainPageCreation() {
        //Pagina care apare in momentul in care un utilizator se logheaza.
        UserMainPage = new JPanel();
        UserMainPage.setLayout(new GridBagLayout());
        UserMainPage.setBackground(new Color(27, 70, 75));

        //Aici am realizat un panel separat pentru butonul destinat paginii de profil si pentru cel destinat paginii de notificari, pentru un design frumos.
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jPanel.add(profilePageButton);
        JButton NotificationButton = new JButton("Notifications");
        NotificationButton.setBackground(new Color(29, 29, 29));
        NotificationButton.setForeground(new Color(30, 11, 50));
        NotificationButton.setPreferredSize(new Dimension(300, 150));
        jPanel.add(NotificationButton);
        NotificationButton.setFocusable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;

        UserMainPage.add(jPanel, gridBagConstraints);
        JButton Logout2 = new JButton("Logout");
        Logout2.setPreferredSize(new Dimension(200, 30));
        Logout2.setEnabled(true);
        Logout2.setFocusable(false);
        gridBagConstraints.gridy++;

        UserMainPage.add(Logout2, gridBagConstraints);

        JButton Friends = new JButton("Friends");
        Friends.setPreferredSize(new Dimension(200, 30));
        Friends.setEnabled(true);
        Friends.setFocusable(false);
        gridBagConstraints.gridy++;
        UserMainPage.add(Friends, gridBagConstraints);

        Logout2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(SplitLogin);
                pack();
            }
        });

        backButton2.removeActionListener(Arrays.stream(backButton2.getActionListeners()).findFirst().get());
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(UserMainPage);
                setVisible(true);
                pack();
            }
        });

        NotificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotificationPanelCreation();
                setContentPane(NotificationSplit);
                pack();
            }
        });

        Friends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aici creez pagina de Consumers - Friends ai utilizatorului curent.
                JSplitPane FriendsPanel = new JSplitPane();

                FriendsPanel.setEnabled(false);
                FriendsPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

                JButton backButtonFromFriends = new JButton("Back");
                backButtonFromFriends.setPreferredSize(new Dimension(FriendsPanel.getLeftComponent().getWidth(), FriendsPanel.getLeftComponent().getHeight()));

                backButtonFromFriends.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setContentPane(UserMainPage);
                        UserMainPage.setVisible(true);
                        pack();
                    }
                });

                JPanel FriendsList = new JPanel();
                JScrollPane FriendsScrollPanel = new JScrollPane(FriendsList);

                FriendsList.setLayout(new GridBagLayout());
                GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
                gridBagConstraints1.gridy = 0;
                gridBagConstraints1.gridx = 0;
                gridBagConstraints.weightx = 0;
                gridBagConstraints1.weighty = 0;
                gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
                gridBagConstraints.anchor = GridBagConstraints.NORTH;

                if ( intrat ) {
                    for (Consumer consumer1 : userLog.consumers) {
                        JButton nume = new JButton(consumer1.cv.information.getNume()+ " " + consumer1.cv.information.getPrenume());
                        nume.setBackground(Color.GREEN);
                        nume.setPreferredSize(new Dimension(FriendsList.getWidth(), 50));
                        nume.setEnabled(true);
                        nume.setFocusable(false);
                        FriendsList.add(nume,gridBagConstraints1);
                        gridBagConstraints1.gridy++;
                    }
                } else {
                    for (Consumer consumer1: employeeLog.consumers){
                        JButton nume = new JButton(consumer1.cv.information.getNume()+ " " + consumer1.cv.information.getPrenume());
                        nume.setBackground(Color.CYAN);
                        nume.setPreferredSize(new Dimension(FriendsList.getWidth(), 50));
                        nume.setEnabled(true);
                        nume.setFocusable(false);
                        FriendsList.add(nume,gridBagConstraints1);
                        gridBagConstraints1.gridy++;
                    }
                }

                gridBagConstraints1.weighty =1;
                gridBagConstraints1.weightx =1;
                FriendsList.add(new JLabel(""), gridBagConstraints1);

                FriendsPanel.setLeftComponent(backButtonFromFriends);
                FriendsPanel.setRightComponent(FriendsScrollPanel);
                setContentPane(FriendsPanel);
                pack();
            }
        });
    }

    public void NotificationPanelCreation() {
        //Aici creez pagina corespunzatoare notificarilor primite de un user.
        NotificationPanel.removeAll();
        NotificScroll.setPreferredSize(new Dimension(NotificationSplit.getRightComponent().getWidth(), NotificationSplit.getRightComponent().getHeight()));
        NotificationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        Application application = Application.getInstance();
        if (intrat) {
            //Daca este un user, atunci are notificari personalizate.
            for (Notification notification : userLog.notifications) {
                JButton NotiType = new JButton(notification.notificationType.toString());
                NotiType.setPreferredSize(new Dimension(NotificationPanel.getWidth(), 25));
                NotiType.setEnabled(true);
                NotiType.setFocusable(false);
                NotiType.setBackground(Color.BLACK);
                NotificationPanel.add(NotiType, gridBagConstraints);
                gridBagConstraints.gridy++;

                JButton NotiCompany, NotiJob;
                NotiCompany = new JButton("Company: " + notification.company.name);
                NotiJob = new JButton("Job: " + notification.job.name);

                NotiCompany.setPreferredSize(new Dimension(NotificationPanel.getWidth(), 50));
                NotiCompany.setEnabled(true);
                NotiCompany.setFocusable(false);
                NotiCompany.setBackground(Color.RED);

                NotiJob.setPreferredSize(new Dimension(NotificationPanel.getWidth(), 50));
                NotiJob.setEnabled(true);
                NotiJob.setFocusable(false);
                NotiJob.setBackground(Color.ORANGE);

                NotificationPanel.add(NotiCompany, gridBagConstraints);
                gridBagConstraints.gridy++;
                NotificationPanel.add(NotiJob, gridBagConstraints);
                gridBagConstraints.gridy++;

                NotificationPanel.add(new JTextField(""), gridBagConstraints);
                gridBagConstraints.gridy++;
            }
        } else {
            //Daca este un employee atunci ni se aduce la cunostinta ca acesta este un employee (deci nu va avea cele mai importante notificari ).
            for (Company company : Application.getInstance().Companies) {
                for (Departament departament : company.departaments) {
                    for (Employee employee : departament.getEmployees()) {
                        if (Login.username.getText().compareTo(employee.cv.information.getEmail()) == 0
                                && Login.password.getText().compareTo(employee.cv.information.getSex()) == 0) {
                            JButton jButton = new JButton("This is an Employee!");
                            jButton.setBackground(Color.BLACK);
                            jButton.setPreferredSize(new Dimension(NotificationPanel.getWidth(), 50));
                            jButton.setEnabled(true);
                            jButton.setFocusable(false);
                            NotificationPanel.add(jButton, gridBagConstraints);
                            gridBagConstraints.gridy++;
                            System.out.println(employee.cv.information.getNume());
                            JButton NotiCompany;
                            NotiCompany = new JButton("Company: " + employee.company);

                            NotiCompany.setPreferredSize(new Dimension(NotificationPanel.getWidth(), 50));
                            NotiCompany.setEnabled(true);
                            NotiCompany.setFocusable(false);
                            NotiCompany.setBackground(Color.RED);

                            NotificationPanel.add(NotiCompany, gridBagConstraints);
                            gridBagConstraints.gridy++;
                        }
                    }
                }
            }
        }

        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;
        NotificationPanel.add(new JLabel(""), gridBagConstraints);
    }

    public void CompanyChoosedPageCreation() {
        //In urma apasarii butonului "admin page", in urma login-ului admin-ului, exista posibilitatea de a alege apoi USERS/COMPANIES.
        //Daca s-a ales Companies - atunci aceasta este functia care creeaza acesta pagina.

        JSplitPane CompanyChoosedPage = new JSplitPane();
        CompanyChoosedPage.setOrientation(JSplitPane.VERTICAL_SPLIT);
        JButton backFromCompanyChoosedPage = new JButton("Back");
        backFromCompanyChoosedPage.setBackground(Color.BLACK);
        backFromCompanyChoosedPage.setEnabled(true);
        backFromCompanyChoosedPage.setPreferredSize(new Dimension(CompanyChoosedPage.getWidth(), 50));
        CompanyChoosedPage.setLeftComponent(backFromCompanyChoosedPage);
        CompanyChoosedPage.setEnabled(false);

        //butonul de back.
        backFromCompanyChoosedPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(UserOrCompany);
                setVisible(true);
                pack();
            }
        });

        splitPaneCompany = new JSplitPane();
        splitPaneCompany.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneCompany.setResizeWeight(0.15);
        splitPaneCompany.setEnabled(false);
        CompanyChoosedPage.setRightComponent(splitPaneCompany);
        LeftCompanyPanel = new JPanel();
        JPanel rightComapanyPanel = new JPanel();
        splitPaneCompany.setLeftComponent(LeftCompanyPanel);
        splitPaneCompany.setRightComponent(rightComapanyPanel);

        LeftCompanyPanel.setPreferredSize(new Dimension(splitPaneCompany.getLeftComponent().getWidth(), splitPaneCompany.getLeftComponent().getHeight()));
        rightComapanyPanel.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));

        //jTabbedPaneCompany = aici au fost initializate panel-urile din jTabbedPaneCompany, impreuna cu componenta lor Scroll care va fi folosita in locul simplelor panel-uri.
        //De asemenea aici se initializeaza splitpanel-urile.
        JTabbedPane jTabbedPaneCompany = new JTabbedPane();

        EmployeesScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        EmployeesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        EmplScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        EmplScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JobsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JobsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        RecrutersScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        RecrutersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        EmployeesPanel.removeAll();
        EmployeeInfo.removeAll();
        JobsPanel.removeAll();
        newEmployee.removeAll();
        RecruitersPanel.removeAll();
        jTabbedPaneCompany.add("Employees", EmployeesScroll);
        jTabbedPaneCompany.add("Employee Info", EmplScroll);
        jTabbedPaneCompany.add("Jobs", JobsScroll);
        jTabbedPaneCompany.add("Recruiters", RecrutersScrollPane);
        jTabbedPaneCompany.add("Add Employee", newEmployeeScroll);
        newEmployee.setVisible(false);
        EmployeesScroll.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));
        EmplScroll.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));
        JobsScroll.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));
        newEmployeeScroll.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));
        RecrutersScrollPane.setPreferredSize(new Dimension(splitPaneCompany.getRightComponent().getWidth(), splitPaneCompany.getRightComponent().getHeight()));
        LeftCompanyPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        jTabbedPaneCompany.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Doar daca un employee este selectat atunci se activeaza butoanele si
                //field-urile de mutare a acestuia in alt departament sau companie.
                if (jTabbedPaneCompany.getSelectedIndex() == 1) {
                    Text2AdminMove.setEnabled(true);
                    TextAdminMove.setEnabled(true);
                    ButtonAdminMove.setEnabled(true);
                } else {
                    Text2AdminMove.setEnabled(false);
                    TextAdminMove.setEnabled(false);
                    ButtonAdminMove.setEnabled(false);
                }
            }
        });

        JSplitPane splitAdminMove = new JSplitPane();
        JPanel adminMove = new JPanel();
        TextAdminMove = new JTextField("Type Company");
        Text2AdminMove = new JTextField("Type Department");
        ButtonAdminMove = new JButton("Move Employee");
        adminMove.setLayout(new GridLayout());
        Text2AdminMove.setEnabled(false);
        TextAdminMove.setEnabled(false);
        ButtonAdminMove.setEnabled(false);
        adminMove.add(TextAdminMove);
        adminMove.add(Text2AdminMove);
        adminMove.add(ButtonAdminMove);

        TextAdminMove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (TextAdminMove.isEnabled()) {
                    TextAdminMove.setText("");
                }
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (TextAdminMove.isEnabled()) {
                    TextAdminMove.setText("");
                }
                super.mousePressed(e);
            }
        });

        Text2AdminMove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Text2AdminMove.isEnabled()) {
                    Text2AdminMove.setText("");
                }
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Text2AdminMove.isEnabled()) {
                    Text2AdminMove.setText("");
                }
                super.mousePressed(e);
            }
        });

        ButtonAdminMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mutarea efectiva a unui angajat.
                if (SelectedEmployee != null) {
                    String companySelected = TextAdminMove.getText();
                    String departmentSelected = Text2AdminMove.getText();

                    Application application = Application.getInstance();
                    if (application.getCompany(companySelected) != null) {
                        Company company6 = application.getCompany(companySelected);
                        for (Departament departament : company6.departaments) {
                            if (departament.getType().compareTo(departmentSelected) == 0) {
                                application.getCompany(SelectedEmployee.company).remove(SelectedEmployee);
                                company6.add(SelectedEmployee, departament);
                                SelectedEmployee.company = companySelected;
                                CompanyChoosedPageCreation();
                                setVisible(true);
                                pack();
                            }
                        }
                    }
                }
            }
        });

        splitAdminMove.setLeftComponent(adminMove);
        splitAdminMove.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitAdminMove.setEnabled(false);

        JSplitPane splitSalaryAdmin = new JSplitPane();
        splitSalaryAdmin.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitSalaryAdmin.setEnabled(false);
        splitSalaryAdmin.setRightComponent(splitAdminMove);

        JPanel salaryPanelAdmin = new JPanel();
        salaryPanelAdmin.setLayout(new GridLayout());

        SalaryText = new JTextField("The salary budget of the department is...");
        SalaryText.setEditable(false);
        SalaryText.setFocusable(false);

        SalaryCalculate = new JButton("Calculate");
        SalaryCalculate.setEnabled(false);
        SalaryCalculate.setFocusable(false);

        SalaryCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalaryText.setText(String.valueOf(departamentSelected.getTotalSalaryBudget()));
            }
        });

        salaryPanelAdmin.add(SalaryText);
        salaryPanelAdmin.add(SalaryCalculate);

        splitSalaryAdmin.setLeftComponent(salaryPanelAdmin);
        splitAdminMove.setRightComponent(jTabbedPaneCompany);
        splitPaneCompany.setRightComponent(splitSalaryAdmin);

        int contor = 0;
        JButton Alege = new JButton("Choose a company");
        Alege.setPreferredSize(new Dimension(LeftCompanyPanel.getWidth(), 25));
        Alege.setBackground(Color.BLACK);
        contor++;
        LeftCompanyPanel.add(Alege, constraints);

        //De aici incepe constructia meniului din stanga la care am depus destul de mult efort.
        //Daca apas pe o companie o data , atunci se deschide lista de departamente.
        //Daca o companie are lista de departamente deschisa si apas pe ea, atunci
        //lista de departamente se inchide.
        //Daca o companie are lista de departamente deschisa si apas pe alta companie, atunci
        //lista de departamente veche se inchide si se deschide cea noua.
        //Astfel meniu-ul este foarte flexibil si de fiecare daca cand se intampla o actiune,
        //celelalte panel-uri ( jobs, employeeInfo, employee se schimba corespunzator).
        Application application = Application.getInstance();

        for (Company company : application.Companies) {
            JButtonCompany jButtonCompany = new JButtonCompany(company, company.name);
            jButtonCompanies.add(jButtonCompany);
            JButton jButton = jButtonCompany.jButton;
            jButton.setPreferredSize(new Dimension(splitPaneCompany.getLeftComponent().getWidth(), 50));
            jButton.setFocusable(false);
            jButton.setBackground(Color.RED);
            constraints.gridy = contor;
            contor++;
            LeftCompanyPanel.add(jButton, constraints);

            jButtonCompany.jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel jPanel = new JPanel();
                    jPanel.setPreferredSize(new Dimension(LeftCompanyPanel.getPreferredSize()));
                    jPanel.setLayout(new GridBagLayout());
                    SalaryCalculate.setEnabled(false);
                    SalaryText.setText("The salary budget of the department is...");
                    splitPaneCompany.setLeftComponent(jPanel);
                    GridBagConstraints constraints1 = new GridBagConstraints();
                    constraints1.gridy = 0;
                    constraints1.gridx = 0;
                    constraints1.weighty = 0;
                    constraints1.weightx = 0;
                    constraints1.fill = GridBagConstraints.HORIZONTAL;
                    constraints1.anchor = GridBagConstraints.NORTH;
                    int contor1 = 0;

                    JButton Alege2 = new JButton("Choose a company");
                    Alege2.setPreferredSize(new Dimension(jPanel.getWidth(), 25));
                    Alege2.setBackground(Color.BLACK);
                    contor1++;
                    jPanel.add(Alege, constraints1);

                    //Pentru fiecare companie
                    for (Company company1 : application.Companies) {
                        JButtonCompany jButton11 = new JButtonCompany(company1, company1.name);
                        JButton jButton1 = jButton11.jButton;
                        jButton1.setPreferredSize(new Dimension(jPanel.getWidth(), 50));
                        jButton1.setFocusable(false);
                        jButton1.setBackground(Color.RED);
                        constraints1.gridy = contor1;
                        contor1++;
                        jPanel.add(jButton1, constraints1);

                        //Daca compania aleasa a fost si cea selectata, atunci pentru ea realizeaza:
                        if (company1.name.compareTo(jButtonCompany.company.name) == 0) {
                            jButton1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Aceasta a fost o companie al carui meniu de departamente a fost inchis");
                                    splitPaneCompany.setLeftComponent(LeftCompanyPanel);
                                    JButton Alege = new JButton("Choose a company");
                                    Alege.setPreferredSize(new Dimension(LeftCompanyPanel.getWidth(), 25));
                                    Alege.setBackground(Color.BLACK);
                                    SalaryCalculate.setEnabled(false);

                                    SalaryText.setText("The salary budget of the department is...");
                                    constraints.gridy = 0;
                                    constraints.weighty = 0;
                                    constraints.weightx = 0;
                                    LeftCompanyPanel.add(Alege, constraints);

                                    //recruiterArrayList.clear();
                                    EmployeeInfo.removeAll();
                                    EmployeesPanel.removeAll();
                                    RecruitersPanel.removeAll();
                                    JobsPanel.removeAll();
                                    newEmployee.removeAll();
                                    LeftCompanyPanel.setVisible(true);
                                    splitPaneCompany.setVisible(true);
                                    pack();
                                }
                            });

                            //Se deschid toate amanuntele pentru o companie, doar atunci cand se deschide. ( JOBS, RECRUITERS si ADDEMPLOYEE - mesaj )

                            RecruitersPanel.setLayout(new GridBagLayout());
                            GridBagConstraints recruiterConstraints = new GridBagConstraints();
                            recruiterConstraints.gridy = 0;
                            recruiterConstraints.gridx = 0;
                            recruiterConstraints.weightx = 0;
                            recruiterConstraints.weighty = 0;
                            recruiterConstraints.fill = GridBagConstraints.HORIZONTAL;
                            recruiterConstraints.anchor = GridBagConstraints.NORTH;
                            int contorRecruiter = 0;

                            //Se creeaza pagina de Recruiters.
                            for (Recruiter recruiter : company1.recruiters) {
                                JButton score = new JButton(String.valueOf(recruiter.rating));
                                score.setPreferredSize(new Dimension(RecruitersPanel.getWidth(), 25));
                                score.setEnabled(true);
                                score.setFocusable(false);
                                score.setBackground(Color.ORANGE);
                                recruiterConstraints.gridy = contorRecruiter;
                                RecruitersPanel.add(score, recruiterConstraints);
                                contorRecruiter++;

                                JButton nameRecruiter = new JButton(recruiter.cv.information.getNume() + " " + recruiter.cv.information.getPrenume());
                                nameRecruiter.setPreferredSize(new Dimension(RecruitersPanel.getWidth(), 50));
                                nameRecruiter.setEnabled(true);
                                nameRecruiter.setFocusable(false);
                                nameRecruiter.setBackground(Color.BLACK);
                                recruiterConstraints.gridy = contorRecruiter;
                                RecruitersPanel.add(nameRecruiter, recruiterConstraints);
                                contorRecruiter++;

                                recruiterConstraints.gridy = contorRecruiter;
                                JLabel jLabel = new JLabel("");
                                jLabel.setPreferredSize(new Dimension(RecruitersPanel.getWidth(), 20));
                                RecruitersPanel.add(jLabel, recruiterConstraints);
                                contorRecruiter++;
                            }

                            recruiterConstraints.weighty = 1;
                            recruiterConstraints.weightx = 1;
                            recruiterConstraints.gridy = contorRecruiter;
                            RecruitersPanel.add(new JLabel(""), recruiterConstraints);

                            newEmployee.setLayout(new CardLayout());
                            JButton jLabelNewEmployee = new JButton("Choose a department");
                            jLabelNewEmployee.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                            newEmployee.add(jLabelNewEmployee);
                            newEmployee.setVisible(true);

                            GridBagLayout JobLayout = new GridBagLayout();
                            GridBagConstraints JobConstraints2 = new GridBagConstraints();
                            JobsPanel.setLayout(JobLayout);
                            JobConstraints2.fill = GridBagConstraints.HORIZONTAL;
                            JobConstraints2.gridx = 0;
                            JobConstraints2.weighty = 0;
                            JobConstraints2.weightx = 0;
                            JobConstraints2.anchor = GridBagConstraints.NORTH;
                            int contorJob = 0;
                            JobsPanel.removeAll();
                            String[] column7 = new String[]{"About", "Date"};

                            //Se creeaza pagina de job-uri.
                            for (Job job : jButtonCompany.company.getJobs()) {
                                JButton NameJob = new JButton(String.valueOf(job.name));
                                NameJob.setEnabled(true);
                                NameJob.setFocusable(false);
                                NameJob.setPreferredSize(new Dimension(JobsPanel.getWidth(), 50));
                                NameJob.setForeground(Color.BLACK);
                                //NameJob.setBackground(Color.GREEN);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(NameJob, JobConstraints2);

                                JButton NameCompany = new JButton(String.valueOf(job.company));
                                NameCompany.setEnabled(true);
                                NameCompany.setFocusable(false);
                                NameCompany.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                NameCompany.setForeground(Color.BLACK);
                                NameCompany.setBackground(Color.YELLOW);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(NameCompany, JobConstraints2);

                                JButton Salariu = new JButton("Salariu");
                                Salariu.setEnabled(true);
                                Salariu.setFocusable(false);
                                Salariu.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                Salariu.setForeground(Color.BLACK);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(Salariu, JobConstraints2);

                                JButton Money = new JButton(String.valueOf(Double.valueOf(job.salary)));
                                Money.setEnabled(true);
                                Money.setFocusable(false);
                                Money.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                Money.setForeground(Color.BLACK);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(Money, JobConstraints2);

                                JButton AnAbsolvire = new JButton("CONSTRANGERE AN ABSOLVIRE");
                                AnAbsolvire.setEnabled(true);
                                AnAbsolvire.setFocusable(false);
                                AnAbsolvire.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                AnAbsolvire.setForeground(Color.BLACK);
                                AnAbsolvire.setBackground(Color.RED);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(AnAbsolvire, JobConstraints2);

                                String[][] data1 = {{"An minim", String.valueOf(Double.valueOf(job.anAbsolvire.inferior).longValue())},
                                        {"An maxim", String.valueOf(Double.valueOf(job.anAbsolvire.superior).longValue())}};
                                if (job.anAbsolvire.inferior == -1) {
                                    data1 = new String[][]{{"An minim", "null"},
                                            {"An maxim", String.valueOf(Double.valueOf(job.anAbsolvire.superior).longValue())}};
                                }
                                if (job.anAbsolvire.superior == 3000) {
                                    data1 = new String[][]{{"An minim", String.valueOf(Double.valueOf(job.anAbsolvire.inferior).longValue())},
                                            {"An maxim", "null"}};
                                }
                                if (job.anAbsolvire.inferior == -1 && job.anAbsolvire.superior == 3000) {
                                    data1 = new String[][]{{"An minim", "null"},
                                            {"An maxim", "null"}};
                                }

                                JTable jTableJob = new JTable(data1, column7);
                                jTableJob.setSize(new Dimension(JobsPanel.getWidth(), 50));
                                jTableJob.setEnabled(false);
                                jTableJob.setFocusable(false);

                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                jTableJob.getColumnModel().getColumn(0).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTableJob.getColumnModel().getColumn(1).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTableJob.setFillsViewportHeight(false);
                                JScrollPane jScrollPaneJobs = new JScrollPane(jTableJob);
                                jScrollPaneJobs.setPreferredSize(new Dimension(jTableJob.getWidth(), 3 * 20));
                                JobsPanel.add(jScrollPaneJobs, JobConstraints2);

                                JButton AniExperienta = new JButton("CONSTRANGERE EXPERIENTA");
                                AniExperienta.setEnabled(true);
                                AniExperienta.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                AniExperienta.setForeground(Color.BLACK);
                                AniExperienta.setBackground(Color.ORANGE);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(AniExperienta, JobConstraints2);

                                String[][] data6 = {{"Experienta minima", String.valueOf(Double.valueOf(job.aniExperienta.inferior).longValue())},
                                        {"Experienta maxima", String.valueOf(Double.valueOf(job.aniExperienta.superior).longValue())}};
                                if (job.aniExperienta.inferior == -1) {
                                    data6 = new String[][]{{"Experienta minima", "null"},
                                            {"Experienta maxima", String.valueOf(Double.valueOf(job.aniExperienta.superior).longValue())}};
                                }
                                if (job.aniExperienta.superior == 3000) {
                                    data6 = new String[][]{{"Experienta minima", String.valueOf(Double.valueOf(job.aniExperienta.inferior).longValue())},
                                            {"Experienta maxima", "null"}};
                                }
                                if (job.aniExperienta.inferior == -1 && job.aniExperienta.superior == 3000) {
                                    data6 = new String[][]{{"Experienta minima", "null"},
                                            {"Experienta maxima", "null"}};
                                }

                                JTable jTable6 = new JTable(data6, column7);
                                jTable6.setSize(new Dimension(JobsPanel.getWidth(), 50));
                                jTable6.setEnabled(false);
                                jTable6.setFocusable(false);

                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                jTable6.getColumnModel().getColumn(0).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTable6.getColumnModel().getColumn(1).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTable6.setFillsViewportHeight(false);
                                JScrollPane jScrollPane6 = new JScrollPane(jTable6);
                                jScrollPane6.setPreferredSize(new Dimension(jTable6.getWidth(), 3 * 20));
                                JobsPanel.add(jScrollPane6, JobConstraints2);

                                JButton MedieNecesara = new JButton("CONSTRANGERE MEDIE");
                                MedieNecesara.setEnabled(true);
                                MedieNecesara.setFocusable(false);
                                MedieNecesara.setPreferredSize(new Dimension(JobsPanel.getWidth(), 25));
                                MedieNecesara.setForeground(Color.BLACK);
                                MedieNecesara.setBackground(Color.BLUE);
                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                JobsPanel.add(MedieNecesara, JobConstraints2);

                                String[][] data7 = {{"Medie minima", String.valueOf(Double.valueOf(job.medieAcademica.inferior).longValue())},
                                        {"Medie maxima", String.valueOf(Double.valueOf(job.medieAcademica.superior).longValue())}};
                                if (job.medieAcademica.inferior == -1) {
                                    data7 = new String[][]{{"Medie minima", "null"},
                                            {"Medie maxima", String.valueOf(Double.valueOf(job.medieAcademica.superior).longValue())}};
                                }
                                if (job.medieAcademica.superior == 3000) {
                                    data7 = new String[][]{{"Medie minima", String.valueOf(Double.valueOf(job.medieAcademica.inferior).longValue())},
                                            {"Medie maxima", "null"}};
                                }
                                if (job.medieAcademica.inferior == -1 && job.medieAcademica.superior == 3000) {
                                    data7 = new String[][]{{"Medie minima", "null"},
                                            {"Medie maxima", "null"}};
                                }

                                JTable jTable7 = new JTable(data7, column7);
                                jTable7.setSize(new Dimension(JobsPanel.getWidth(), 50));
                                jTable7.setEnabled(false);
                                jTable7.setFocusable(false);

                                JobConstraints2.gridy = contorJob;
                                contorJob++;
                                jTable7.getColumnModel().getColumn(0).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTable7.getColumnModel().getColumn(1).setPreferredWidth(JobsPanel.getWidth() / 2);
                                jTable7.setFillsViewportHeight(false);
                                JScrollPane jScrollPane7 = new JScrollPane(jTable7);
                                jScrollPane7.setPreferredSize(new Dimension(jTable7.getWidth(), 3 * 20));
                                JobsPanel.add(jScrollPane7, JobConstraints2);
                            }
                            JobConstraints2.weightx = 1;
                            JobConstraints2.weighty = 1;
                            JobConstraints2.gridy = contorJob;
                            JobsPanel.add(new JLabel(" "), JobConstraints2);
                            JobsPanel.setVisible(true);

                            EmployeesPanel.removeAll();
                            EmployeesPanel.setVisible(true);

                            pack();

                            //Se realizeaza lista de meniuri de departamente impreuna cu toate schimbarile din celelalte panel-uri care se leaga de aceasta schimbare.
                            for (Departament departament : jButtonCompany.company.departaments) {
                                JButtonDepartment jButtonDepartment = new JButtonDepartment(departament, departament.getType());
                                JButton jButton2 = jButtonDepartment.jButton;
                                jButton2.setPreferredSize(new Dimension(jPanel.getWidth(), 25));
                                jButton2.setFocusable(false);
                                jButton2.setBackground(Color.BLUE);
                                SalaryCalculate.setEnabled(false);

                                //Atunci cand se apasa pe un departament.
                                jButton2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //Se creeaza pagina de Employee.
                                        EmployeesPanel.setLayout(new GridBagLayout());
                                        EmployeesPanel.removeAll();
                                        newEmployee.removeAll();
                                        SalaryCalculate.setEnabled(true);
                                        departamentSelected = departament;
                                        jButtonDepartmentForAddEmployee = jButtonDepartment;

                                        GridBagConstraints bagConstraints = new GridBagConstraints();
                                        bagConstraints.gridy = 0;
                                        bagConstraints.gridx = 0;
                                        bagConstraints.weightx = 0;
                                        bagConstraints.weighty = 0;
                                        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
                                        bagConstraints.anchor = GridBagConstraints.NORTH;

                                        //Se adauga tipul departamentului - buton dezactivat, albastru.
                                        JButton DepartmentType = new JButton(departament.getType());
                                        DepartmentType.setFocusable(false);
                                        DepartmentType.setEnabled(false);
                                        DepartmentType.setPreferredSize(new Dimension(EmployeesPanel.getWidth(), 25));
                                        DepartmentType.setBackground(Color.blue);
                                        EmployeesPanel.add(DepartmentType, bagConstraints);

                                        int contorEmployees = 1;
                                        for (Employee employee : jButtonDepartment.departament.getEmployees()) {
                                            //Pentru fiecare employee, adaug un buton cu numele sau si o actiune a butonului.
                                            JButton employeeData = new JButton(employee.cv.information.getNume() + " " + employee.cv.information.getPrenume());
                                            employeeData.setEnabled(true);
                                            employeeData.setFocusable(false);
                                            SalaryCalculate.setEnabled(true);
                                            employeeData.setPreferredSize(new Dimension(EmployeesPanel.getWidth(), 50));
                                            employeeData.setForeground(Color.BLACK);

                                            employeeData.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    //Atunci cand un employee este selectat, jtabbedPane isi schimba index-ul si se creeaza pagina noua - EmployeeInfo.
                                                    EmployeeInfo.removeAll();
                                                    EmployeeInfo.setLayout(new GridBagLayout());
                                                    SelectedEmployee = employee;

                                                    GridBagConstraints constraints2 = new GridBagConstraints();
                                                    constraints2.weightx = 0;
                                                    constraints2.weighty = 0;
                                                    constraints2.gridx = 0;
                                                    int contor2 = 1;
                                                    constraints2.gridy = 0;
                                                    constraints2.fill = GridBagConstraints.HORIZONTAL;
                                                    constraints2.anchor = GridBagConstraints.NORTH;

                                                    JButton DepartmentType2 = new JButton(jButtonDepartment.departament.getType());
                                                    DepartmentType2.setFocusable(false);
                                                    DepartmentType2.setEnabled(false);
                                                    DepartmentType2.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 25));
                                                    DepartmentType2.setBackground(Color.blue);
                                                    EmployeeInfo.add(DepartmentType2, constraints2);

                                                    JButton name = new JButton(employee.cv.information.getNume() + " " + employee.cv.information.getPrenume());
                                                    name.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    name.setFocusable(false);
                                                    name.setBackground(Color.ORANGE);
                                                    constraints2.gridy = contor2;
                                                    EmployeeInfo.add(name, constraints2);
                                                    contor2++;

                                                    JButton company = new JButton(employee.company);
                                                    company.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 25));
                                                    company.setFocusable(false);
                                                    company.setBackground(Color.GREEN);
                                                    constraints2.gridy = contor2;
                                                    EmployeeInfo.add(company, constraints2);
                                                    contor2++;

                                                    JButton salary = new JButton(employee.salary.toString());
                                                    salary.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 25));
                                                    salary.setFocusable(false);
                                                    salary.setBackground(Color.blue);
                                                    constraints2.gridy = contor2;
                                                    EmployeeInfo.add(salary, constraints2);
                                                    contor2++;

                                                    JButton information = new JButton("Information");
                                                    information.setEnabled(true);
                                                    information.setFocusable(false);
                                                    information.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    information.setForeground(Color.BLACK);
                                                    information.setBackground(Color.YELLOW);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    EmployeeInfo.add(information, constraints2);

                                                    String[][] data4 = {{"Nume", employee.cv.information.getNume()},
                                                            {"Prenume", employee.cv.information.getPrenume()},
                                                            {"Sex", employee.cv.information.getSex()},
                                                            {"Data de nastere", employee.cv.information.getData_de_nastere()},
                                                            {"Telefon", employee.cv.information.getTelefon()},
                                                            {"Email", employee.cv.information.getEmail()}};

                                                    String[] column = new String[]{"About", "Date"};
                                                    JTable jTable = new JTable(data4, column);
                                                    jTable.setSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    jTable.setEnabled(false);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    jTable.getColumnModel().getColumn(0).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                    jTable.getColumnModel().getColumn(1).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                    jTable.setFillsViewportHeight(false);
                                                    JScrollPane jScrollPane = new JScrollPane(jTable);
                                                    jScrollPane.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (7) * jTable.getRowHeight()));
                                                    if (jScrollPane.isWheelScrollingEnabled()) {
                                                        jScrollPane.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (7) * jTable.getRowHeight() + 11));
                                                    }
                                                    EmployeeInfo.add(jScrollPane, constraints2);

                                                    JButton limbi = new JButton("Limbi");
                                                    limbi.setEnabled(true);
                                                    limbi.setFocusable(false);
                                                    limbi.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    limbi.setForeground(Color.BLACK);
                                                    limbi.setBackground(Color.DARK_GRAY);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    EmployeeInfo.add(limbi, constraints2);

                                                    String[] column2 = new String[]{"Limba", "Nivel"};
                                                    String[][] data2 = new String[employee.cv.information.getLanguages().size()][2];
                                                    int i = 0;
                                                    for (Limbi language : employee.cv.information.getLanguages()) {
                                                        data2[i][0] = language.getLimba();
                                                        data2[i][1] = language.getLevel();
                                                        i++;
                                                    }
                                                    JTable jTable2 = new JTable(data2, column2);
                                                    jTable2.setSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    jTable2.setEnabled(false);
                                                    jTable2.setFocusable(false);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    jTable2.getColumnModel().getColumn(0).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                    jTable2.getColumnModel().getColumn(1).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                    jTable2.setFillsViewportHeight(false);
                                                    JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                                                    jScrollPane2.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (employee.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight()));
                                                    if (jScrollPane2.isWheelScrollingEnabled()) {
                                                        jScrollPane2.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (employee.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight() + 11));
                                                    }
                                                    EmployeeInfo.add(jScrollPane2, constraints2);

                                                    JButton Educations = new JButton("Educations");
                                                    Educations.setEnabled(true);
                                                    Educations.setFocusable(false);
                                                    Educations.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    Educations.setForeground(Color.BLACK);
                                                    Educations.setBackground(Color.BLUE);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    EmployeeInfo.add(Educations, constraints2);

                                                    for (Education education : employee.cv.educations) {
                                                        JButton buttons = new JButton();
                                                        buttons.setFocusable(false);
                                                        if (education.end != null) {
                                                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                                                    education.end.get(Calendar.DATE) + "." + education.end.get(Calendar.MONTH) + "." + education.end.get(Calendar.YEAR));
                                                        } else {
                                                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                                                    "now");
                                                        }
                                                        buttons.setEnabled(true);
                                                        buttons.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 25));
                                                        buttons.setForeground(Color.BLACK);
                                                        buttons.setBackground(Color.RED);
                                                        constraints2.gridy = contor2;
                                                        contor2++;
                                                        EmployeeInfo.add(buttons, constraints2);

                                                        String[][] data3 = {{"Level", education.level},
                                                                {"Institutie", education.institutie},
                                                                {"Medie", education.medie.toString()}};

                                                        JTable jTable3 = new JTable(data3, column);
                                                        jTable3.setEnabled(false);
                                                        jTable3.setFocusable(false);
                                                        jTable3.setSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                        constraints2.gridy = contor2;
                                                        contor2++;
                                                        jTable3.getColumnModel().getColumn(0).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                        jTable3.getColumnModel().getColumn(1).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                        jTable3.setFillsViewportHeight(false);
                                                        JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                                                        jScrollPane3.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (4 * jTable3.getRowHeight())));
                                                        if (jScrollPane3.isWheelScrollingEnabled()) {
                                                            jScrollPane3.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (4 * jTable3.getRowHeight() + 11)));
                                                        }
                                                        EmployeeInfo.add(jScrollPane3, constraints2);
                                                    }

                                                    JButton Experiences = new JButton("Experiences");
                                                    Experiences.setEnabled(true);
                                                    Experiences.setFocusable(false);
                                                    Experiences.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                    Experiences.setForeground(Color.BLACK);
                                                    Experiences.setBackground(Color.BLUE);
                                                    constraints2.gridy = contor2;
                                                    contor2++;
                                                    EmployeeInfo.add(Experiences, constraints2);

                                                    for (Experience experience : employee.cv.experiences) {
                                                        JButton buttonsExperience = new JButton();
                                                        buttonsExperience.setFocusable(false);
                                                        if (experience.end != null) {
                                                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                                                    experience.end.get(Calendar.DATE) + "." + experience.end.get(Calendar.MONTH) + "." + experience.end.get(Calendar.YEAR));
                                                        } else {
                                                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                                                    "now");
                                                        }
                                                        buttonsExperience.setEnabled(true);
                                                        buttonsExperience.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), 25));
                                                        buttonsExperience.setForeground(Color.BLACK);
                                                        buttonsExperience.setBackground(Color.RED);
                                                        constraints2.gridy = contor2;
                                                        contor2++;
                                                        EmployeeInfo.add(buttonsExperience, constraints2);

                                                        String[][] data5 = {{"Company", experience.company},
                                                                {"Departament", experience.department},
                                                                {"Pozitie", experience.pozitie}};

                                                        JTable jTable4 = new JTable(data5, column);
                                                        jTable4.setSize(new Dimension(EmployeeInfo.getWidth(), 50));
                                                        constraints2.gridy = contor2;
                                                        contor2++;
                                                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(EmployeeInfo.getWidth() / 2);
                                                        jTable4.setFillsViewportHeight(false);
                                                        jTable4.setEnabled(false);
                                                        jTable4.setFocusable(false);
                                                        JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                                                        jScrollPane4.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (4 * jTable4.getRowHeight())));
                                                        if (jScrollPane4.isWheelScrollingEnabled()) {
                                                            jScrollPane4.setPreferredSize(new Dimension(EmployeeInfo.getWidth(), (4 * jTable4.getRowHeight() + 11)));
                                                        }
                                                        EmployeeInfo.add(jScrollPane4, constraints2);

                                                        pack();
                                                    }

                                                    constraints2.gridy = contor2;
                                                    constraints2.weighty = 1;
                                                    constraints2.weightx = 1;
                                                    EmployeeInfo.add(new JLabel(""), constraints2);
                                                    jTabbedPaneCompany.setSelectedIndex(1);
                                                    EmployeeInfo.setVisible(true);
                                                    pack();
                                                }
                                            });

                                            bagConstraints.gridy = contorEmployees;
                                            contorEmployees++;
                                            EmployeesPanel.add(employeeData, bagConstraints);
                                        }


                                        //Atunci cand se selecteaza un departament se activeaza si pagina de adaugare a unui nou angajat.
                                        //Astfel dupa ce se va implementa un nou angajat, acesta va fi adaugat in compania si departamentul selectat.
                                        newEmployee.setLayout(new GridBagLayout());
                                        GridBagConstraints newConstraint = new GridBagConstraints();
                                        newConstraint.gridy = 0;
                                        newConstraint.gridx = 0;
                                        newConstraint.weightx = 0;
                                        newConstraint.weighty = 0;
                                        newConstraint.anchor = GridBagConstraints.NORTH;
                                        newConstraint.fill = GridBagConstraints.HORIZONTAL;
                                        int newContor = 0;

                                        JButton Adauga = new JButton("Add employee");
                                        Adauga.setEnabled(false);
                                        Adauga.setFocusable(false);
                                        Adauga.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(Adauga, newConstraint);

                                        JButton companyName = new JButton(company1.name);
                                        companyName.setEnabled(true);
                                        companyName.setFocusable(false);
                                        companyName.setBackground(Color.BLACK);
                                        companyName.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(companyName, newConstraint);

                                        JButton departmentName = new JButton(jButtonDepartment.departament.getType());
                                        departmentName.setEnabled(true);
                                        departmentName.setFocusable(false);
                                        departmentName.setBackground(Color.BLACK);
                                        departmentName.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(departmentName, newConstraint);

                                        JButton addSalary = new JButton("Add Salary");
                                        addSalary.setEnabled(false);
                                        addSalary.setFocusable(false);
                                        addSalary.setBackground(Color.BLACK);
                                        addSalary.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(addSalary, newConstraint);

                                        JTextField salaryInput = new JTextField("*required*");
                                        salaryInput.setEditable(true);
                                        salaryInput.setEnabled(true);
                                        salaryInput.setHorizontalAlignment(SwingConstants.CENTER);
                                        salaryInput.setFocusable(true);
                                        salaryInput.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(salaryInput, newConstraint);

                                        salaryInput.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                salaryInput.setText("");
                                                super.mouseClicked(e);
                                            }

                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                salaryInput.setText("");
                                                super.mousePressed(e);
                                            }
                                        });

                                        JButton information = new JButton("Add Information");
                                        information.setEnabled(false);
                                        information.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        information.setForeground(Color.BLACK);
                                        information.setBackground(Color.YELLOW);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(information, newConstraint);

                                        String[][] data = {{"Nume", "*required*"},
                                                {"Prenume", "*required*"},
                                                {"Sex", "*required*"},
                                                {"Data de nastere", "*required*"},
                                                {"Telefon", "*required*"},
                                                {"Email", "*required*"}};

                                        String[] column = new String[]{"About", "Date - press enter to commit"};
                                        JTable jTable = new JTable(data, column);
                                        jTable.setEnabled(true);
                                        jTable.setFocusable(false);
                                        jTable.setSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        jTable.getColumnModel().getColumn(0).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable.getColumnModel().getColumn(1).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable.setFillsViewportHeight(false);
                                        JScrollPane jScrollPane = new JScrollPane(jTable);
                                        jScrollPane.setPreferredSize(new Dimension(newEmployee.getWidth(), (7) * jTable.getRowHeight()));
                                        if (jScrollPane.isWheelScrollingEnabled()) {
                                            jScrollPane.setPreferredSize(new Dimension(newEmployee.getWidth(), (7) * jTable.getRowHeight() + 11));
                                        }
                                        newEmployee.add(jScrollPane, newConstraint);

                                        JButton limbi = new JButton("Add Languages");
                                        limbi.setEnabled(false);
                                        limbi.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        limbi.setForeground(Color.BLACK);
                                        limbi.setBackground(Color.ORANGE);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(limbi, newConstraint);

                                        String[][] data4 = {{"Limba", "*required*"},
                                                {"Nivel", "*required*"}};

                                        JTable jTable4 = new JTable(data4, column);
                                        jTable4.setEnabled(true);
                                        jTable4.setFocusable(false);
                                        jTable4.setSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable4.setFillsViewportHeight(false);
                                        JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                                        jScrollPane4.setPreferredSize(new Dimension(newEmployee.getWidth(), (3) * jTable4.getRowHeight()));
                                        if (jScrollPane4.isWheelScrollingEnabled()) {
                                            jScrollPane4.setPreferredSize(new Dimension(newEmployee.getWidth(), (3) * jTable4.getRowHeight() + 11));
                                        }
                                        newEmployee.add(jScrollPane4, newConstraint);

                                        JButton addlimba = new JButton("Click to add Language");
                                        addlimba.setEnabled(false);
                                        addlimba.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        addlimba.setForeground(Color.BLACK);
                                        addlimba.setBackground(Color.ORANGE);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(addlimba, newConstraint);

                                        JButton educations = new JButton("Add Educations");
                                        educations.setEnabled(false);
                                        educations.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        educations.setForeground(Color.BLACK);
                                        educations.setBackground(Color.RED);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(educations, newConstraint);

                                        String[][] data2 = {{"level", "*required*"},
                                                {"name", "*required*"},
                                                {"start_date", "*required* - dd:MM:yyyy"},
                                                {"end_date", "*required* - dd:MM:yyyy"},
                                                {"grade", "*required*"}};

                                        JTable jTable2 = new JTable(data2, column);
                                        jTable2.setEnabled(true);
                                        jTable2.setFocusable(false);
                                        jTable2.setSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        jTable2.getColumnModel().getColumn(0).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable2.getColumnModel().getColumn(1).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable2.setFillsViewportHeight(false);
                                        JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                                        jScrollPane2.setPreferredSize(new Dimension(newEmployee.getWidth(), (6) * jTable2.getRowHeight()));
                                        if (jScrollPane2.isWheelScrollingEnabled()) {
                                            jScrollPane2.setPreferredSize(new Dimension(newEmployee.getWidth(), (6) * jTable2.getRowHeight() + 11));
                                        }
                                        newEmployee.add(jScrollPane2, newConstraint);

                                        JButton addeducatie = new JButton("Click to add this education");
                                        addeducatie.setEnabled(false);
                                        addeducatie.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        addeducatie.setForeground(Color.BLACK);
                                        addeducatie.setBackground(Color.RED);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(addeducatie, newConstraint);

                                        JButton experiences = new JButton("Add Experiences");
                                        experiences.setEnabled(false);
                                        experiences.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                        experiences.setForeground(Color.BLACK);
                                        experiences.setBackground(Color.blue);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(experiences, newConstraint);

                                        String[][] data3 = {{"company", "*required*"},
                                                {"position", "*required*"},
                                                {"start_date", "*required* - dd:MM:yyyy"},
                                                {"end_date", "*required* - dd:MM:yyyy"},
                                                {"department", "*required*"}};

                                        JTable jTable3 = new JTable(data3, column);
                                        jTable3.setEnabled(true);
                                        jTable3.setFocusable(false);
                                        jTable3.setSize(new Dimension(newEmployee.getWidth(), 50));
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        jTable3.getColumnModel().getColumn(0).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable3.getColumnModel().getColumn(1).setPreferredWidth(newEmployee.getWidth() / 2);
                                        jTable3.setFillsViewportHeight(false);
                                        JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                                        jScrollPane3.setPreferredSize(new Dimension(newEmployee.getWidth(), (6) * jTable3.getRowHeight()));
                                        if (jScrollPane3.isWheelScrollingEnabled()) {
                                            jScrollPane3.setPreferredSize(new Dimension(newEmployee.getWidth(), (6) * jTable3.getRowHeight() + 11));
                                        }
                                        newEmployee.add(jScrollPane3, newConstraint);

                                        JButton addexperienta = new JButton("Click to add this experience");
                                        addexperienta.setEnabled(false);
                                        addexperienta.setPreferredSize(new Dimension(newEmployee.getWidth(), 25));
                                        addexperienta.setForeground(Color.BLACK);
                                        addexperienta.setBackground(Color.RED);
                                        newConstraint.gridy = newContor;
                                        newContor++;
                                        newEmployee.add(addexperienta, newConstraint);

                                        final int[] contorTabel = {0};

                                        //Doar daca toate field-uri din tabel sunt completate, atunci butonul de information se activeaza.
                                        //Dupa ce toate butoanele mai groase ( de grosime  50 ) se activeaza, doar atunci se activeaza si butonul principal addEmployee.
                                        jTable.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                JTable target = (JTable) e.getSource();
                                                int row = target.getSelectedRow();
                                                int column = target.getSelectedColumn();
                                                Object o = jTable.getValueAt(row, column);
                                                if (o.toString().compareTo("*required*") == 0) {
                                                    contorTabel[0]++;
                                                }
                                                if (contorTabel[0] == 6) {
                                                    information.setEnabled(true);

                                                    if (limbi.isEnabled() && educations.isEnabled() && experiences.isEnabled() && addSalary.isEnabled()) {
                                                        Adauga.setEnabled(true);
                                                    }
                                                }
                                                jTable.setValueAt("", row, column);
                                                super.mousePressed(e);
                                            }
                                        });

                                        final int[] contorTabel4 = {0};
                                        //Pentru tabelul de limbi, aceeasi regula, daca toate campurile sunt completate, atunci butonul de addlimba se aciveaza, dar acesta este doar un
                                        //buton normal. In schimb daca o limba se va introduce in sistem, atunci butonul principal gros acestei sectiuni ("Add Languages") - se va activa.
                                        jTable4.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mousePressed(MouseEvent e) {
                                                JTable target = (JTable) e.getSource();
                                                int row = target.getSelectedRow();
                                                int column = target.getSelectedColumn();
                                                Object o = jTable4.getValueAt(row, column);
                                                if (o.toString().compareTo("*required*") == 0) {
                                                    contorTabel4[0]++;
                                                }
                                                if (contorTabel4[0] == 2) {
                                                    addlimba.setEnabled(true);
                                                }
                                                jTable4.setValueAt("", row, column);
                                                super.mousePressed(e);
                                            }
                                        });

                                        ArrayList<Limbi> limbiArrayList = new ArrayList<>();

                                        try {
                                            addlimba.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    limbi.setEnabled(true);
                                                    //se verifica, daca toate celelalte butoane secundare , "groase" sunt activate, atunci butonul "Adauga" - butonul principal aduagarii unui nou employee se va activa.
                                                    if (information.isEnabled() && educations.isEnabled() && experiences.isEnabled() && addSalary.isEnabled()) {
                                                        Adauga.setEnabled(true);
                                                    }
                                                    //In rest, aici se adauga o limba noua in sistem, citind input-ul.
                                                    addlimba.setEnabled(false);
                                                    contorTabel4[0] = 0;

                                                    String limba_languages = (String) jTable4.getValueAt(0, 1);

                                                    String level_languages = (String) jTable4.getValueAt(1, 1);

                                                    Limbi limba = new Limbi();
                                                    limba.setLimba(limba_languages);
                                                    switch (level_languages) {
                                                        case "Beginner" -> {
                                                            limba.setBeginner(1);
                                                            limba.setAdvanced(0);
                                                            limba.setExperienced(0);
                                                        }
                                                        case "Advanced" -> {
                                                            limba.setBeginner(0);
                                                            limba.setAdvanced(1);
                                                            limba.setExperienced(0);
                                                        }
                                                        case "Experienced" -> {
                                                            limba.setBeginner(0);
                                                            limba.setAdvanced(0);
                                                            limba.setExperienced(1);
                                                        }
                                                    }
                                                    limbiArrayList.add(limba);

                                                    int i = 1;
                                                    for (int j = 0; j <= 1; j++) {
                                                        //Se reseteaza campurile obligatorii.
                                                        jTable4.setValueAt("*required*", j, i);
                                                    }
                                                }
                                            });

                                            final int[] contorTabel2 = {0};
                                            //Analog, tabel Limbi.
                                            jTable2.addMouseListener(new MouseAdapter() {
                                                @Override
                                                public void mousePressed(MouseEvent e) {
                                                    JTable target = (JTable) e.getSource();
                                                    int row = target.getSelectedRow();
                                                    int column = target.getSelectedColumn();
                                                    Object o = jTable2.getValueAt(row, column);
                                                    if (o.toString().compareTo("*required*") == 0 || o.toString().compareTo("*required* - dd:MM:yyyy") == 0) {
                                                        contorTabel2[0]++;
                                                    }
                                                    if (contorTabel2[0] == 5) {
                                                        addeducatie.setEnabled(true);
                                                    }
                                                    jTable2.setValueAt("", row, column);
                                                    super.mousePressed(e);
                                                }
                                            });

                                            TreeSet<Education> educationTreeSet = new TreeSet<>();
                                            addeducatie.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    educations.setEnabled(true);
                                                    if (limbi.isEnabled() && information.isEnabled() && experiences.isEnabled() && addSalary.isEnabled()) {
                                                        Adauga.setEnabled(true);
                                                    }
                                                    addeducatie.setEnabled(false);
                                                    contorTabel2[0] = 0;

                                                    String level_education = (String) jTable2.getValueAt(0, 1);

                                                    String name_education = (String) jTable2.getValueAt(1, 1);

                                                    String start_date = (String) jTable2.getValueAt(2, 1);
                                                    Calendar calendarStart = Calendar.getInstance();
                                                    String end_date = (String) jTable2.getValueAt(3, 1);
                                                    Calendar calendarEnd = Calendar.getInstance();

                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                                    try {
                                                        calendarStart.setTime(sdf.parse(start_date));
                                                        calendarStart.add(Calendar.DATE, 1);
                                                        calendarEnd.setTime(sdf.parse(end_date));
                                                        calendarEnd.add(Calendar.DATE, 1);
                                                    } catch (ParseException parseException) {
                                                        parseException.printStackTrace();
                                                        calendarStart = null;
                                                        calendarEnd = null;
                                                        JFrame f = new JFrame();
                                                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                        JDialog d = new JDialog(f, "Input data are wrong", true);
                                                        d.setLayout(new FlowLayout());
                                                        d.add(new JLabel("Input data wrong, retry by choosing again the company | the department."));
                                                        d.setSize(500, 150);
                                                        d.setVisible(true);
                                                        pack();
                                                    }

                                                    String double_education = (String) jTable2.getValueAt(4, 1);
                                                    double grad;
                                                    try {
                                                        Double grade1 = Double.valueOf(double_education);
                                                        grad = grade1;
                                                    } catch (Exception e1) {
                                                        Integer grade1 = Integer.valueOf(double_education);
                                                        grad = (double) grade1;
                                                    }

                                                    educationTreeSet.add(new Education(calendarStart, calendarEnd, name_education, level_education, grad));
                                                    int i = 1;
                                                    for (int j = 0; j <= 4; j++) {
                                                        if (j != 3 && j != 2) {
                                                            jTable2.setValueAt("*required*", j, i);
                                                        } else {
                                                            jTable2.setValueAt("*required* - dd:MM:yyyy", j, i);
                                                        }
                                                    }
                                                }
                                            });

                                            final int[] contorTabel3 = {0};
                                            //experienta
                                            jTable3.addMouseListener(new MouseAdapter() {
                                                @Override
                                                public void mousePressed(MouseEvent e) {
                                                    JTable target = (JTable) e.getSource();
                                                    int row = target.getSelectedRow();
                                                    int column = target.getSelectedColumn();
                                                    Object o = jTable3.getValueAt(row, column);
                                                    if (o.toString().compareTo("*required*") == 0 || o.toString().compareTo("*required* - dd:MM:yyyy") == 0) {
                                                        contorTabel3[0]++;
                                                    }
                                                    if (contorTabel3[0] == 5) {
                                                        addexperienta.setEnabled(true);
                                                    }
                                                    jTable3.setValueAt("", row, column);
                                                    super.mousePressed(e);
                                                }
                                            });

                                            TreeSet<Experience> experienceTreeSet = new TreeSet<>();
                                            //Analog tabel education, limbi.
                                            addexperienta.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    addexperienta.setEnabled(false);
                                                    experiences.setEnabled(true);
                                                    if (limbi.isEnabled() && educations.isEnabled() && information.isEnabled() && addSalary.isEnabled()) {
                                                        Adauga.setEnabled(true);
                                                    }
                                                    contorTabel3[0] = 0;

                                                    String company_experience = (String) jTable3.getValueAt(0, 1);

                                                    String position_experience = (String) jTable3.getValueAt(1, 1);

                                                    String start_date = (String) jTable3.getValueAt(2, 1);
                                                    Calendar calendarStart = Calendar.getInstance();
                                                    String end_date = (String) jTable3.getValueAt(3, 1);
                                                    Calendar calendarEnd = Calendar.getInstance();

                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                                    try {
                                                        calendarStart.setTime(sdf.parse(start_date));
                                                        calendarStart.add(Calendar.DATE, 1);
                                                        calendarEnd.setTime(sdf.parse(end_date));
                                                        calendarEnd.add(Calendar.DATE, 1);
                                                    } catch (ParseException parseException) {
                                                        parseException.printStackTrace();
                                                        calendarStart = null;
                                                        calendarEnd = null;
                                                        JFrame f = new JFrame();
                                                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                        JDialog d = new JDialog(f, "Input data are wrong", true);
                                                        d.setLayout(new FlowLayout());
                                                        d.add(new JLabel("Input data wrong, retry by choosing again the company | the department."));
                                                        d.setSize(500, 150);
                                                        d.setVisible(true);
                                                        pack();
                                                    }

                                                    String department_experience = (String) jTable3.getValueAt(4, 1);

                                                    experienceTreeSet.add(new Experience(calendarStart, calendarEnd, position_experience, company_experience, department_experience));
                                                    int i = 1;
                                                    for (int j = 0; j <= 4; j++) {
                                                        if (j != 3 && j != 2) {
                                                            jTable3.setValueAt("*required*", j, i);
                                                        } else {
                                                            jTable3.setValueAt("*required* - dd:MM:yyyy", j, i);
                                                        }
                                                    }
                                                }
                                            });

                                            //Foarte important, aici in salary input nu accept orice intrare, ci doar cifre , "punct" , enter, backspace si delete.
                                            salaryInput.addKeyListener(new KeyAdapter() {
                                                @Override
                                                public void keyTyped(KeyEvent e) {
                                                    char c = e.getKeyChar();
                                                    if (!((c >= '0') && (c <= '9') || (c == '.') ||
                                                            (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_ENTER) ||
                                                            (c == KeyEvent.VK_DELETE))) {
                                                        getToolkit().beep();
                                                        e.consume();
                                                    } else {
                                                        addSalary.setEnabled(true);
                                                        if (limbi.isEnabled() && educations.isEnabled() && experiences.isEnabled() && information.isEnabled()) {
                                                            Adauga.setEnabled(true);
                                                        }
                                                    }
                                                    super.keyTyped(e);
                                                }
                                            });

                                            Adauga.addActionListener(new ActionListener() {
                                                //Se adauga un nou employee in sistem.
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    String Nume = (String) jTable.getValueAt(0, 1);
                                                    String Prenume = (String) jTable.getValueAt(1, 1);
                                                    String Sex = (String) jTable.getValueAt(2, 1);
                                                    String Data_de_nastere = (String) jTable.getValueAt(3, 1);
                                                    String Telefon = (String) jTable.getValueAt(4, 1);
                                                    String Email = (String) jTable.getValueAt(5, 1);

                                                    Information information1 = new Information();
                                                    information1.setNume(Nume);
                                                    information1.setPrenume(Prenume);
                                                    information1.setSex(Sex);
                                                    information1.setData_de_nastere(Data_de_nastere);
                                                    information1.setTelefon(Telefon);
                                                    information1.setEmail(Email);
                                                    information1.setLanguages(limbiArrayList);

                                                    Resume.ResumeBuilder builder = new Resume.ResumeBuilder();
                                                    builder.information(information1);
                                                    builder.education(educationTreeSet);
                                                    builder.experiences(experienceTreeSet);
                                                    Resume resume = builder.build();

                                                    String salariu = salaryInput.getText();
                                                    double salaryAdmin;
                                                    try {
                                                        Double grade1 = Double.valueOf(salariu);
                                                        salaryAdmin = grade1;
                                                    } catch (Exception e1) {
                                                        Integer grade1 = Integer.valueOf(salariu);
                                                        salaryAdmin = (double) grade1;
                                                    }

                                                    Employee employee = new Employee(resume, company1.name, salaryAdmin);
                                                    Company company2 = application.getCompany(employee.company);
                                                    company2.add(employee, jButtonDepartment.departament);
                                                    jTabbedPaneCompany.setSelectedIndex(0);
                                                    jButtonDepartmentForAddEmployee.jButton.doClick();
                                                }
                                            });
                                        } catch (Exception e2) {
                                            JFrame f = new JFrame();
                                            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            JDialog d = new JDialog(f, "Input data are wrong", true);
                                            d.setLayout(new FlowLayout());
                                            d.add(new JLabel("Input data wrong, retry by choosing again the company | the department."));
                                            d.setSize(500, 150);
                                            d.setVisible(true);
                                            pack();
                                        }

                                        newConstraint.gridy = newContor;
                                        newConstraint.weighty = 1;
                                        newConstraint.weightx = 1;
                                        newEmployee.add(new JLabel(""), newConstraint);
                                        newEmployee.setVisible(true);

                                        bagConstraints.weightx = 1;
                                        bagConstraints.weighty = 1;
                                        bagConstraints.gridy = contorEmployees;
                                        EmployeesPanel.add(new JLabel(""), bagConstraints);
                                        EmployeesPanel.setVisible(true);
                                        jTabbedPaneCompany.setSelectedIndex(1);
                                        jTabbedPaneCompany.setSelectedIndex(0);

                                        pack();
                                    }
                                });

                                constraints1.gridy = contor1;
                                contor1++;
                                jPanel.add(jButton2, constraints1);
                            }
                        } else {
                            //Aici se aplica ActionEvent-urile pentru celelalte companii care nu au fost selectate;
                            jButton1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //Se steaza ca Panel al componentei stanga a splitPane-lui , LEFTCOMPANYPANEL = panel-ul in care nici un meniu nu este deschis.
                                    splitPaneCompany.setLeftComponent(LeftCompanyPanel);
                                    for (JButtonCompany jButtonCompany1 : jButtonCompanies) {
                                        if (jButtonCompany1.company.name.compareTo(jButton11.company.name) == 0) {
                                            //Daca se doreste inchiderea meniu-ului de departamente a unei companii si deschiderea meniu-ului de departamente
                                            //a altei companii in acelasi timp.
                                            RecruitersPanel.removeAll();
                                            EmployeeInfo.removeAll();
                                            SalaryCalculate.setEnabled(false);
                                            newEmployee.setLayout(new CardLayout());
                                            JButton jLabelNewEmployee = new JButton("Choose a department");
                                            jLabelNewEmployee.setPreferredSize(new Dimension(newEmployee.getWidth(), 50));
                                            newEmployee.add(jLabelNewEmployee);
                                            newEmployee.setVisible(true);
                                            pack();
                                            //Se activeaza butonul de deschidere al setului nou de lista de departamente prin aceasta metoda.
                                            //Pentru a realiza acest lucru am folosit butoane speciale din clasa JButtonCompany.
                                            jButtonCompany1.jButton.doClick();
                                        }
                                    }

                                    splitPaneCompany.setVisible(true);
                                    pack();
                                }
                            });
                        }
                    }
                    constraints1.weightx = 1;
                    constraints1.weighty = 1;
                    constraints1.gridy = contor1;
                    jPanel.add(new JLabel(" "), constraints1);
                    jPanel.setVisible(true);
                    pack();
                }
            });
        }

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridy = contor;
        LeftCompanyPanel.add(new JLabel(" "), constraints);

        setContentPane(CompanyChoosedPage);
        setVisible(true);
        pack();
    }

    public void UserChoosedPageCreation() {
        //Pagina de users, din adminPage.
        SplitUsersAdmin.setResizeWeight(0.15);
        SplitUsersAdmin.setEnabled(false);

        JPanel usersList = new JPanel();
        usersList.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0;
        constraints.weighty = 0;

        UserInfo = new JPanel();
        UserInfo.setLayout(new GridBagLayout());
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0;
        constraints1.fill = GridBagConstraints.HORIZONTAL;
        constraints1.weightx = 0;
        constraints1.weighty = 0;

        JButton UserInfoButton = new JButton("User Info");
        UserInfoButton.setBackground(Color.BLUE);
        UserInfoButton.setPreferredSize(new Dimension(UserInfo.getWidth(), 20));
        UserInfo.add(UserInfoButton, constraints1);

        constraints1.gridy = 1;
        constraints1.weighty = 1;
        constraints1.weightx = 1;
        UserInfo.add(new JLabel(" "), constraints1);
        UserInfo = new JPanel();
        SplitUsersAdmin.setRightComponent(UserInfo);
        UserInfo.setVisible(true);
        pack();

        JButton ChooseUser = new JButton("Choose an user");
        ChooseUser.setBackground(Color.BLUE);
        ChooseUser.setPreferredSize(new Dimension(usersList.getWidth(), 20));
        usersList.add(ChooseUser, constraints);

        JScrollPane jScrollPane1 = new JScrollPane(UserInfo);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        SplitUsersAdmin.setRightComponent(jScrollPane1);

        int contor = 1;
        Application application = Application.getInstance();
        for (User user : application.Users) {
            JButton jButton = new JButton(user.cv.information.getNume() + " " + user.cv.information.getPrenume());
            jButton.setBackground(Color.GREEN);
            jButton.setPreferredSize(new Dimension(usersList.getWidth(), 40));

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userfromList = user;
                    UserInfo.removeAll();
                    //UserInfo.setPreferredSize(SplitUsersAdmin.getRightComponent().getPreferredSize());
                    UserInfo.setLayout(new GridBagLayout());
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.weightx = 0;
                    constraints.weighty = 0;

                    int contor = 0;
                    JButton UserInfoButton = new JButton("User Info");
                    UserInfoButton.setBackground(Color.BLUE);
                    UserInfoButton.setPreferredSize(new Dimension(UserInfo.getWidth(), 20));
                    UserInfo.add(UserInfoButton, constraints);
                    contor++;

                    JButton nume = new JButton(user.cv.information.getNume() + " " + user.cv.information.getPrenume());
                    nume.setEnabled(true);
                    nume.setPreferredSize(new Dimension(UserInfo.getWidth(), 50));
                    nume.setForeground(Color.BLACK);
                    nume.setBackground(Color.GREEN);
                    UserInfo.add(nume, constraints);
                    contor++;

                    JButton information = new JButton("Information");
                    information.setEnabled(true);
                    information.setPreferredSize(new Dimension(UserInfo.getWidth(), 50));
                    information.setForeground(Color.BLACK);
                    information.setBackground(Color.YELLOW);
                    constraints.gridy = contor;
                    contor++;
                    UserInfo.add(information, constraints);

                    String[][] data = {{"Nume", user.cv.information.getNume()},
                            {"Prenume", user.cv.information.getPrenume()},
                            {"Sex", user.cv.information.getSex()},
                            {"Data de nastere", user.cv.information.getData_de_nastere()},
                            {"Telefon", user.cv.information.getTelefon()},
                            {"Email", user.cv.information.getEmail()}};

                    String[] column = new String[]{"About", "Date"};
                    JTable jTable = new JTable(data, column);
                    jTable.setSize(new Dimension(UserInfo.getWidth(), 50));
                    jTable.setEnabled(false);
                    constraints.gridy = contor;
                    contor++;
                    jTable.getColumnModel().getColumn(0).setPreferredWidth(UserInfo.getWidth() / 2);
                    jTable.getColumnModel().getColumn(1).setPreferredWidth(UserInfo.getWidth() / 2);
                    jTable.setFillsViewportHeight(false);
                    JScrollPane jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setPreferredSize(new Dimension(UserInfo.getWidth(), (7) * jTable.getRowHeight()));
                    if (jScrollPane.isWheelScrollingEnabled()) {
                        jScrollPane.setPreferredSize(new Dimension(UserInfo.getWidth(), (7) * jTable.getRowHeight() + 11));
                    }
                    UserInfo.add(jScrollPane, constraints);

                    JButton limbi = new JButton("Limbi");
                    limbi.setEnabled(true);
                    limbi.setPreferredSize(new Dimension(UserInfo.getWidth(), 50));
                    limbi.setForeground(Color.BLACK);
                    limbi.setBackground(Color.DARK_GRAY);
                    constraints.gridy = contor;
                    contor++;
                    UserInfo.add(limbi, constraints);

                    String[] column2 = new String[]{"Limba", "Nivel"};
                    String[][] data2 = new String[user.cv.information.getLanguages().size()][2];
                    int i = 0;
                    for (Limbi language : user.cv.information.getLanguages()) {
                        data2[i][0] = language.getLimba();
                        data2[i][1] = language.getLevel();
                        i++;
                    }
                    JTable jTable2 = new JTable(data2, column2);
                    jTable2.setSize(new Dimension(UserInfo.getWidth(), 50));
                    jTable2.setEnabled(false);
                    constraints.gridy = contor;
                    contor++;
                    jTable2.getColumnModel().getColumn(0).setPreferredWidth(UserInfo.getWidth() / 2);
                    jTable2.getColumnModel().getColumn(1).setPreferredWidth(UserInfo.getWidth() / 2);
                    jTable2.setFillsViewportHeight(false);
                    JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                    jScrollPane2.setPreferredSize(new Dimension(UserInfo.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight()));
                    if (jScrollPane2.isWheelScrollingEnabled()) {
                        jScrollPane2.setPreferredSize(new Dimension(UserInfo.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight() + 11));
                    }
                    UserInfo.add(jScrollPane2, constraints);

                    JButton Educations = new JButton("Educations");
                    Educations.setEnabled(true);
                    Educations.setPreferredSize(new Dimension(UserInfo.getWidth(), 50));
                    Educations.setForeground(Color.BLACK);
                    Educations.setBackground(Color.BLUE);
                    constraints.gridy = contor;
                    contor++;
                    UserInfo.add(Educations, constraints);

                    for (Education education : user.cv.educations) {
                        JButton buttons = new JButton();
                        if (education.end != null) {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    education.end.get(Calendar.DATE) + "." + education.end.get(Calendar.MONTH) + "." + education.end.get(Calendar.YEAR));
                        } else {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttons.setEnabled(true);
                        buttons.setPreferredSize(new Dimension(UserInfo.getWidth(), 25));
                        buttons.setForeground(Color.BLACK);
                        buttons.setBackground(Color.RED);
                        constraints.gridy = contor;
                        contor++;
                        UserInfo.add(buttons, constraints);

                        String[][] data3 = {{"Level", education.level},
                                {"Institutie", education.institutie},
                                {"Medie", education.medie.toString()}};

                        JTable jTable3 = new JTable(data3, column);
                        jTable3.setEnabled(false);
                        jTable3.setSize(new Dimension(UserInfo.getWidth(), 50));
                        constraints.gridy = contor;
                        contor++;
                        jTable3.getColumnModel().getColumn(0).setPreferredWidth(UserInfo.getWidth() / 2);
                        jTable3.getColumnModel().getColumn(1).setPreferredWidth(UserInfo.getWidth() / 2);
                        jTable3.setFillsViewportHeight(false);
                        JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                        jScrollPane3.setPreferredSize(new Dimension(UserInfo.getWidth(), (4 * jTable3.getRowHeight())));
                        if (jScrollPane3.isWheelScrollingEnabled()) {
                            jScrollPane3.setPreferredSize(new Dimension(UserInfo.getWidth(), (4 * jTable3.getRowHeight() + 11)));
                        }
                        UserInfo.add(jScrollPane3, constraints);
                    }

                    JButton Experiences = new JButton("Experiences");
                    Experiences.setEnabled(true);
                    Experiences.setPreferredSize(new Dimension(UserInfo.getWidth(), 50));
                    Experiences.setForeground(Color.BLACK);
                    Experiences.setBackground(Color.BLUE);
                    constraints.gridy = contor;
                    contor++;
                    UserInfo.add(Experiences, constraints);

                    for (Experience experience : user.cv.experiences) {
                        JButton buttonsExperience = new JButton();
                        if (experience.end != null) {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    experience.end.get(Calendar.DATE) + "." + experience.end.get(Calendar.MONTH) + "." + experience.end.get(Calendar.YEAR));
                        } else {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttonsExperience.setEnabled(true);
                        buttonsExperience.setPreferredSize(new Dimension(UserInfo.getWidth(), 25));
                        buttonsExperience.setForeground(Color.BLACK);
                        buttonsExperience.setBackground(Color.RED);
                        constraints.gridy = contor;
                        contor++;
                        UserInfo.add(buttonsExperience, constraints);

                        String[][] data4 = {{"Company", experience.company},
                                {"Departament", experience.department},
                                {"Pozitie", experience.pozitie}};

                        JTable jTable4 = new JTable(data4, column);
                        jTable4.setSize(new Dimension(UserInfo.getWidth(), 50));
                        constraints.gridy = contor;
                        contor++;
                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(UserInfo.getWidth() / 2);
                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(UserInfo.getWidth() / 2);
                        jTable4.setFillsViewportHeight(false);
                        jTable4.setEnabled(false);
                        JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                        jScrollPane4.setPreferredSize(new Dimension(UserInfo.getWidth(), (4 * jTable4.getRowHeight())));
                        if (jScrollPane4.isWheelScrollingEnabled()) {
                            jScrollPane4.setPreferredSize(new Dimension(UserInfo.getWidth(), (4 * jTable4.getRowHeight() + 11)));
                        }
                        UserInfo.add(jScrollPane4, constraints);
                    }

                    constraints.gridy = contor;
                    constraints.weighty = 1;
                    constraints.weightx = 1;
                    UserInfo.add(new JLabel(" "), constraints);

                    UserInfo.setVisible(true);
                    setVisible(true);
                    pack();
                }
            });

            constraints.gridy = contor;
            usersList.add(jButton, constraints);
            contor++;
        }

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridy = contor;
        usersList.add(new JLabel(" "), constraints);
        SplitUsersAdmin.setLeftComponent(usersList);
        usersList.setVisible(true);
        setContentPane(UserChoosedPage);
        setVisible(true);
        pack();
    }

    public void UserOrCompanyPageCreation() {
        SplitFromUserOrCompany.setResizeWeight(0.52);
        SplitFromUserOrCompany.setEnabled(false);
    }

    public void ManagerPageCreation() {
        Application application = Application.getInstance();

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(70);
        splitPane.setEnabled(false);
        JPanel jPanelTopManager = new JPanel();
        splitPane.setLeftComponent(jPanelTopManager);
        jPanelTopManager.setLayout(new GridLayout());

        JButton ProcessButton = new JButton("Process Automate");
        ProcessButton.setBackground(Color.BLUE);
        ProcessButton.setEnabled(false);
        jPanelTopManager.add(ProcessButton);

        AcceptButton = new JButton("Accept");
        AcceptButton.setBackground(Color.GREEN);
        AcceptButton.setEnabled(false);
        jPanelTopManager.add(AcceptButton);

        CancelButton = new JButton("Cancel");
        CancelButton.setBackground(Color.RED);
        CancelButton.setEnabled(false);
        jPanelTopManager.add(CancelButton);

        JSplitPane companynameSplit = new JSplitPane();
        companynameSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
        companynameSplit.setDividerLocation(30);
        companynameSplit.setEnabled(false);

        JTextField companyFinder = new JTextField();
        companyFinder.setText("Te rog sa introduci numele companiei:");

        companyFinder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                companyFinder.setText("");
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                companyFinder.setText("");
                super.mousePressed(e);
            }
        });

        companyFinder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //Verifica tipul de acces, daca este accesat de admin sau de un manager si restrictioneaza accesul.
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    companyName = companyFinder.getText();
                    Application application = Application.getInstance();
                    if (application.getCompany(companyName) != null) {
                        if (ManagerType == 2) {
                            ProcessButton.setEnabled(true);
                            jTabbedPane.setSelectedIndex(1);
                            jTabbedPane.setSelectedIndex(0);
                            showRequests();
                        }
                        if (ManagerType == 1){
                            if (Manager.company.compareTo(companyName) == 0) {
                                ProcessButton.setEnabled(true);
                                jTabbedPane.setSelectedIndex(1);
                                jTabbedPane.setSelectedIndex(0);
                                showRequests();
                            } else {
                                JFrame f = new JFrame();
                                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                JDialog d = new JDialog(f, "Input data are wrong", true);
                                d.setLayout(new FlowLayout());
                                d.add(new JLabel("The manager does not belong to the company"));
                                d.setSize(500, 150);
                                d.setVisible(true);
                                pack();
                            }
                        }
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    companyName = companyFinder.getText();
                    Application application = Application.getInstance();
                    if (application.getCompany(companyName) != null) {
                        if (ManagerType == 2) {
                            jTabbedPane.setSelectedIndex(1);
                            jTabbedPane.setSelectedIndex(0);
                            ProcessButton.setEnabled(true);
                            showRequests();
                        }
                        if (ManagerType == 1){
                            if (Manager.company.compareTo(companyName) == 0) {
                                ProcessButton.setEnabled(true);
                                jTabbedPane.setSelectedIndex(1);
                                jTabbedPane.setSelectedIndex(0);
                                showRequests();
                            } else {
                                JFrame f = new JFrame();
                                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                JDialog d = new JDialog(f, "Input data are wrong", true);
                                d.setLayout(new FlowLayout());
                                d.add(new JLabel("The manager does not belong to the company"));
                                d.setSize(500, 150);
                                d.setVisible(true);
                                pack();
                            }
                        }
                    }
                }
            }
        });

        JPanel PanelCompanieSearch = new JPanel();
        PanelCompanieSearch.setLayout(new GridLayout());
        PanelCompanieSearch.add(companyFinder);

        JButton ButtonForSearch = new JButton("Cauta");
        ButtonForSearch.setMaximumSize(new Dimension(30, PanelCompanieSearch.getHeight()));
        PanelCompanieSearch.add(ButtonForSearch);

        Requests = new JPanel();
        UserRequest = new JPanel();
        JobRequest = new JPanel();
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add("Request", Requests);
        jTabbedPane.add("User's CV", UserRequest);
        jTabbedPane.add("Job info", JobRequest);

        //Butonul de Accept si de Cancel se activeaza doar atunci cand se selcteaza un employee.
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane.getSelectedIndex() == 1) {
                    if (ProcessButton.isEnabled()) {
                        AcceptButton.setEnabled(true);
                        CancelButton.setEnabled(true);
                    } else {
                        AcceptButton.setEnabled(false);
                        CancelButton.setEnabled(false);
                    }
                } else {
                    AcceptButton.setEnabled(false);
                    CancelButton.setEnabled(false);
                }
            }
        });

        JScrollPane jScrollPaneForRequest = new JScrollPane(jTabbedPane);
        jScrollPaneForRequest.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneForRequest.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Requests.setPreferredSize(companynameSplit.getRightComponent().getPreferredSize());
        companynameSplit.setLeftComponent(PanelCompanieSearch);
        companynameSplit.setRightComponent(jScrollPaneForRequest);

        splitPane.setRightComponent(companynameSplit);
        ManagerPane.setRightComponent(splitPane);
        ManagerPane.setEnabled(false);

        //ProcessButton apeleaza Manager.process().
        ProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application application1 = Application.getInstance();
                for (Company company : application1.getCompanies()) {
                    if (company.name.compareTo(companyName) == 0) {
                        for (Job job : company.getJobs()) {
                            company.manager.process(job);
                        }
                    }
                }
                jTabbedPane.setSelectedIndex(0);
                UserRequest.removeAll();
                Requests.removeAll();
                JobRequest.removeAll();
                jTabbedPane.setSelectedIndex(1);
                jTabbedPane.setSelectedIndex(0);
                ProcessButton.setEnabled(false);
                setVisible(true);
                pack();
            }
        });

        ButtonForSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                companyName = companyFinder.getText();
                Application application = Application.getInstance();
                if (application.getCompany(companyName) != null) {
                    if (ManagerType ==2 ) {
                        ProcessButton.setEnabled(true);
                        jTabbedPane.setSelectedIndex(1);
                        jTabbedPane.setSelectedIndex(0);
                        showRequests();
                    }
                    if (ManagerType == 1){
                        if (Manager.company.compareTo(companyName) == 0) {
                            ProcessButton.setEnabled(true);
                            jTabbedPane.setSelectedIndex(1);
                            jTabbedPane.setSelectedIndex(0);
                            showRequests();
                        } else {
                            //Daca manager-ul nu isi acceseaza propria companie.
                            JFrame f = new JFrame();
                            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JDialog d = new JDialog(f, "Input data are wrong", true);
                            d.setLayout(new FlowLayout());
                            d.add(new JLabel("The manager does not belong to the company"));
                            d.setSize(500, 150);
                            d.setVisible(true);
                            pack();
                        }
                    }
                }
            }
        });

        AcceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request<Job, Consumer> request = AccesedRequest.request;
                Manager manager = application.getCompany(companyName).manager;
                if (request.getKey().noPositions != 0) {
                    //se poate angaja
                    request.getKey().noPositions--;
                    Company company = application.getCompany(request.getKey().company);
                    application.remove((User) request.getValue1());
                    request.getKey().removeObserver((User) request.getValue1());
                    Employee employee = ((User) request.getValue1()).convert();
                    employee.salary = request.getKey().salary;
                    employee.company = request.getKey().company;
                    manager.requests.remove(request);
                    Notification notification3 = new Notification(company, request.getKey(), (User) request.getValue1());
                    notification3.notificationType = Notification.NotificationType.Accepted;
                    ((User) request.getValue1()).update(notification3);
                    Departament departament = company.search(request.getKey());
                    departament.add(employee);
                    ArrayList<Company> companies = application.getCompanies();
                    for (Company company1 : companies) {
                        company1.users.remove((User) request.getValue1());
                    }

                    if (0 == request.getKey().noPositions) {
                        request.getKey().valabil = false;

                        manager.requests.removeIf(request1 -> request1.getKey().equals(request.getKey()));
                        for (Company company1 : companies) {
                            company1.manager.requests.removeIf(request1 -> request1.getValue1().equals(request.getValue1()));
                        }

                        Notification notification1 = new Notification(company, request.getKey());
                        notification1.notificationType = Notification.NotificationType.RequestDeclined;
                        request.getKey().makeNotification(notification1);
                        request.getKey().notifyAllObservers();

                        Notification notification2 = new Notification(company, request.getKey());
                        notification2.notificationType = Notification.NotificationType.ClosedJob;
                        company.makeNotification(notification2);
                        company.notifyAllObservers();

                        if (manager.requests.isEmpty()) {
                            ProcessButton.setEnabled(false);
                            jTabbedPane.setSelectedIndex(0);
                            UserRequest.removeAll();
                            Requests.removeAll();
                            JobRequest.removeAll();
                            jTabbedPane.setSelectedIndex(1);
                            jTabbedPane.setSelectedIndex(0);
                            setVisible(true);
                            pack();
                        } else {
                            jTabbedPane.setSelectedIndex(0);
                            UserRequest.removeAll();
                            Requests.removeAll();
                            JobRequest.removeAll();
                            jTabbedPane.setSelectedIndex(1);
                            jTabbedPane.setSelectedIndex(0);
                            showRequests();
                            setVisible(true);
                            pack();
                        }
                    }
                }
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request<Job, Consumer> request = AccesedRequest.request;
                Manager manager = application.getCompany(companyName).manager;
                Company company = application.getCompany(request.getKey().company);
                request.getKey().removeObserver((User) request.getValue1());
                manager.requests.remove(request);

                Notification notification1 = new Notification(company, request.getKey());
                notification1.notificationType = Notification.NotificationType.RequestDeclined;
                request.getKey().makeNotification(notification1);
                ((User) request.getValue1()).update(notification1);

                if (manager.requests.isEmpty()) {
                    ProcessButton.setEnabled(false);
                    jTabbedPane.setSelectedIndex(0);
                    UserRequest.removeAll();
                    Requests.removeAll();
                    JobRequest.removeAll();
                    jTabbedPane.setSelectedIndex(1);
                    jTabbedPane.setSelectedIndex(0);
                    setVisible(true);
                    pack();
                } else {
                    jTabbedPane.setSelectedIndex(0);
                    UserRequest.removeAll();
                    Requests.removeAll();
                    JobRequest.removeAll();
                    jTabbedPane.setSelectedIndex(1);
                    jTabbedPane.setSelectedIndex(0);
                    showRequests();
                    setVisible(true);
                    pack();
                }
            }
        });
    }

    public void showRequests() {
        Requests.removeAll();
        JobRequest.removeAll();
        UserRequest.removeAll();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints bagConstraints = new GridBagConstraints();
        Requests.setLayout(gridBagLayout);
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.gridx = 0;
        bagConstraints.weighty = 0;
        bagConstraints.weightx = 0;
        bagConstraints.anchor = GridBagConstraints.NORTH;
        int contor = 0;

        Application application = Application.getInstance();
        Manager manager = (application.getCompany(companyName)).manager;

        int i = 0;

        //Creeaza pagina de requests si celelalte pagini inrudite.
        for (Request<Job, Consumer> request : manager.requests) {
            bagConstraints.gridy = contor;
            JLabel labeltest = new JLabel(request.getKey().name);
            JButtonEdited jButtonEdited;
            jButtonEdited = new JButtonEdited(request, request.getValue1().cv.information.getNume() + " " + request.getValue1().cv.information.getPrenume());
            JButton jButton = jButtonEdited.jButton;
            jButton.setHorizontalTextPosition(SwingConstants.CENTER);
            jButton.setFocusable(false);
            jButton.add(labeltest);
            jButton.setEnabled(true);
            jButton.setPreferredSize(new Dimension(Requests.getWidth(), 50));

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UserRequest.removeAll();
                    JobRequest.removeAll();
                    jTabbedPane.setSelectedIndex(1);
                    AccesedRequest = jButtonEdited;
                    //USER PAGE

                    GridBagLayout UserLayout = new GridBagLayout();
                    GridBagConstraints UserConstraints = new GridBagConstraints();
                    UserRequest.setLayout(UserLayout);
                    UserConstraints.fill = GridBagConstraints.HORIZONTAL;
                    UserConstraints.gridx = 0;
                    UserConstraints.weighty = 0;
                    UserConstraints.weightx = 0;
                    UserConstraints.anchor = GridBagConstraints.NORTH;
                    int contorUser = 0;

                    JButton Scor = new JButton(String.valueOf(request.getScore()));
                    Scor.setEnabled(true);
                    Scor.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                    Scor.setForeground(Color.BLACK);
                    Scor.setBackground(Color.GREEN);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    UserRequest.add(Scor, UserConstraints);

                    JButton nume = new JButton(request.getValue1().cv.information.getNume() + " " + request.getValue1().cv.information.getPrenume());
                    nume.setEnabled(true);
                    nume.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                    nume.setForeground(Color.BLACK);
                    nume.setBackground(Color.GREEN);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    UserRequest.add(nume, UserConstraints);

                    String[][] data = {{"Nume", request.getValue1().cv.information.getNume()},
                            {"Prenume", request.getValue1().cv.information.getPrenume()},
                            {"Sex", request.getValue1().cv.information.getSex()},
                            {"Data de nastere", request.getValue1().cv.information.getData_de_nastere()},
                            {"Telefon", request.getValue1().cv.information.getTelefon()},
                            {"Email", request.getValue1().cv.information.getEmail()}};

                    String[] column = new String[]{"About", "Date"};
                    JTable jTable = new JTable(data, column);
                    jTable.setSize(new Dimension(UserRequest.getWidth(), 50));
                    jTable.setEnabled(false);

                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    jTable.getColumnModel().getColumn(0).setPreferredWidth(UserRequest.getWidth() / 2);
                    jTable.getColumnModel().getColumn(1).setPreferredWidth(UserRequest.getWidth() / 2);
                    jTable.setFillsViewportHeight(false);
                    JScrollPane jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setPreferredSize(new Dimension(UserRequest.getWidth(), 6 * 21));
                    UserRequest.add(jScrollPane, UserConstraints);

                    JButton limbi = new JButton("Limbi");
                    limbi.setEnabled(true);
                    limbi.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    limbi.setForeground(Color.BLACK);
                    limbi.setBackground(Color.DARK_GRAY);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    UserRequest.add(limbi, UserConstraints);

                    String[] column2 = new String[]{"Limba", "Nivel"};
                    String[][] data2 = new String[request.getValue1().cv.information.getLanguages().size()][2];
                    int i = 0;
                    for (Limbi language : request.getValue1().cv.information.getLanguages()) {
                        data2[i][0] = language.getLimba();
                        data2[i][1] = language.getLevel();
                        i++;
                    }
                    JTable jTable2 = new JTable(data2, column2);
                    jTable2.setSize(new Dimension(UserRequest.getWidth(), 50));
                    jTable2.setEnabled(false);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    jTable2.getColumnModel().getColumn(0).setPreferredWidth(UserRequest.getWidth() / 2);
                    jTable2.getColumnModel().getColumn(1).setPreferredWidth(UserRequest.getWidth() / 2);
                    jTable2.setFillsViewportHeight(false);
                    JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                    jScrollPane2.setPreferredSize(new Dimension(UserRequest.getWidth(), (request.getValue1().cv.information.getLanguages().size() + 1) * jTable2.getRowHeight()));
                    if (jScrollPane2.isWheelScrollingEnabled()) {
                        jScrollPane2.setPreferredSize(new Dimension(UserRequest.getWidth(), (request.getValue1().cv.information.getLanguages().size() + 1) * jTable2.getRowHeight() + 11));
                    }
                    UserRequest.add(jScrollPane2, UserConstraints);

                    JButton Educations = new JButton("Educations");
                    Educations.setEnabled(true);
                    Educations.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                    Educations.setForeground(Color.BLACK);
                    Educations.setBackground(Color.BLUE);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    UserRequest.add(Educations, UserConstraints);

                    for (Education education : request.getValue1().cv.educations) {
                        JButton buttons = new JButton();
                        if (education.end != null) {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    education.end.get(Calendar.DATE) + "." + education.end.get(Calendar.MONTH) + "." + education.end.get(Calendar.YEAR));
                        } else {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttons.setEnabled(true);
                        buttons.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                        buttons.setForeground(Color.BLACK);
                        buttons.setBackground(Color.RED);
                        UserConstraints.gridy = contorUser;
                        contorUser++;
                        UserRequest.add(buttons, UserConstraints);

                        String[][] data3 = {{"Level", education.level},
                                {"Institutie", education.institutie},
                                {"Medie", education.medie.toString()}};

                        JTable jTable3 = new JTable(data3, column);
                        jTable3.setEnabled(false);
                        jTable3.setSize(new Dimension(UserRequest.getWidth(), 50));
                        UserConstraints.gridy = contorUser;
                        contorUser++;
                        jTable3.getColumnModel().getColumn(0).setPreferredWidth(UserRequest.getWidth() / 2);
                        jTable3.getColumnModel().getColumn(1).setPreferredWidth(UserRequest.getWidth() / 2);
                        jTable3.setFillsViewportHeight(false);
                        JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                        jScrollPane3.setPreferredSize(new Dimension(UserRequest.getWidth(), (4 * jTable3.getRowHeight())));
                        if (jScrollPane3.isWheelScrollingEnabled()) {
                            jScrollPane3.setPreferredSize(new Dimension(UserRequest.getWidth(), (4 * jTable3.getRowHeight() + 11)));
                        }
                        UserRequest.add(jScrollPane3, UserConstraints);
                    }

                    JButton Experiences = new JButton("Experiences");
                    Experiences.setEnabled(true);
                    Experiences.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                    Experiences.setForeground(Color.BLACK);
                    Experiences.setBackground(Color.BLUE);
                    UserConstraints.gridy = contorUser;
                    contorUser++;
                    UserRequest.add(Experiences, UserConstraints);

                    for (Experience experience : request.getValue1().cv.experiences) {
                        JButton buttonsExperience = new JButton();
                        if (experience.end != null) {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    experience.end.get(Calendar.DATE) + "." + experience.end.get(Calendar.MONTH) + "." + experience.end.get(Calendar.YEAR));
                        } else {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttonsExperience.setEnabled(true);
                        buttonsExperience.setPreferredSize(new Dimension(UserRequest.getWidth(), 25));
                        buttonsExperience.setForeground(Color.BLACK);
                        buttonsExperience.setBackground(Color.RED);
                        UserConstraints.gridy = contorUser;
                        contorUser++;
                        UserRequest.add(buttonsExperience, UserConstraints);

                        String[][] data4 = {{"Company", experience.company},
                                {"Departament", experience.department},
                                {"Pozitie", experience.pozitie}};

                        JTable jTable4 = new JTable(data4, column);
                        jTable4.setSize(new Dimension(UserRequest.getWidth(), 50));
                        UserConstraints.gridy = contorUser;
                        contorUser++;
                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(UserRequest.getWidth() / 2);
                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(UserRequest.getWidth() / 2);
                        jTable4.setFillsViewportHeight(false);
                        jTable4.setEnabled(false);
                        JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                        jScrollPane4.setPreferredSize(new Dimension(UserRequest.getWidth(), (4 * jTable4.getRowHeight())));
                        if (jScrollPane4.isWheelScrollingEnabled()) {
                            jScrollPane4.setPreferredSize(new Dimension(UserRequest.getWidth(), (4 * jTable4.getRowHeight() + 11)));
                        }
                        UserRequest.add(jScrollPane4, UserConstraints);
                    }

                    UserConstraints.weightx = 1;
                    UserConstraints.weighty = 1;
                    UserRequest.add(new JLabel(" "), UserConstraints);
                    UserRequest.setVisible(true);

                    //JOB PAGE
                    GridBagLayout JobLayout = new GridBagLayout();
                    GridBagConstraints JobConstraints = new GridBagConstraints();
                    JobRequest.setLayout(JobLayout);
                    JobConstraints.fill = GridBagConstraints.HORIZONTAL;
                    JobConstraints.gridx = 0;
                    JobConstraints.weighty = 0;
                    JobConstraints.weightx = 0;
                    JobConstraints.anchor = GridBagConstraints.NORTH;
                    int contorJob = 0;

                    JButton NameJob = new JButton(String.valueOf(request.getKey().name));
                    NameJob.setEnabled(true);
                    NameJob.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    NameJob.setForeground(Color.BLACK);
                    NameJob.setBackground(Color.GREEN);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(NameJob, JobConstraints);

                    JButton NameCompany = new JButton(String.valueOf(request.getKey().company));
                    NameCompany.setEnabled(true);
                    NameCompany.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    NameCompany.setForeground(Color.BLACK);
                    NameCompany.setBackground(Color.YELLOW);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(NameCompany, JobConstraints);

                    JButton AnAbsolvire = new JButton("CONSTRANGERE AN ABSOLVIRE");
                    AnAbsolvire.setEnabled(true);
                    AnAbsolvire.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    AnAbsolvire.setForeground(Color.BLACK);
                    AnAbsolvire.setBackground(Color.RED);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(AnAbsolvire, JobConstraints);

                    String[][] data1 = {{"An minim", String.valueOf(Double.valueOf(request.getKey().anAbsolvire.inferior).longValue())},
                            {"An maxim", String.valueOf(Double.valueOf(request.getKey().anAbsolvire.superior).longValue())}};
                    if (request.getKey().anAbsolvire.inferior == -1) {
                        data1 = new String[][]{{"An minim", "null"},
                                {"An maxim", String.valueOf(Double.valueOf(request.getKey().anAbsolvire.superior).longValue())}};
                    }
                    if (request.getKey().anAbsolvire.superior == 3000) {
                        data1 = new String[][]{{"An minim", String.valueOf(Double.valueOf(request.getKey().anAbsolvire.inferior).longValue())},
                                {"An maxim", "null"}};
                    }
                    if (request.getKey().anAbsolvire.inferior == -1 && request.getKey().anAbsolvire.superior == 3000) {
                        data1 = new String[][]{{"An minim", "null"},
                                {"An maxim", "null"}};
                    }

                    JTable jTable4 = new JTable(data1, column);
                    jTable4.setSize(new Dimension(JobRequest.getWidth(), 50));
                    jTable4.setEnabled(false);

                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    jTable4.getColumnModel().getColumn(0).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable4.getColumnModel().getColumn(1).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable4.setFillsViewportHeight(false);
                    JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                    jScrollPane4.setPreferredSize(new Dimension(jTable4.getWidth(), 3 * 20));
                    JobRequest.add(jScrollPane4, JobConstraints);

                    JButton AniExperienta = new JButton("CONSTRANGERE EXPERIENTA");
                    AniExperienta.setEnabled(true);
                    AniExperienta.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    AniExperienta.setForeground(Color.BLACK);
                    AniExperienta.setBackground(Color.ORANGE);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(AniExperienta, JobConstraints);

                    String[][] data5 = {{"Experienta minima", String.valueOf(Double.valueOf(request.getKey().aniExperienta.inferior).longValue())},
                            {"Experienta maxima", String.valueOf(Double.valueOf(request.getKey().aniExperienta.superior).longValue())}};
                    if (request.getKey().aniExperienta.inferior == -1) {
                        data5 = new String[][]{{"Experienta minima", "null"},
                                {"Experienta maxima", String.valueOf(Double.valueOf(request.getKey().aniExperienta.superior).longValue())}};
                    }
                    if (request.getKey().aniExperienta.superior == 3000) {
                        data5 = new String[][]{{"Experienta minima", String.valueOf(Double.valueOf(request.getKey().aniExperienta.inferior).longValue())},
                                {"Experienta maxima", "null"}};
                    }
                    if (request.getKey().aniExperienta.inferior == -1 && request.getKey().aniExperienta.superior == 3000) {
                        data5 = new String[][]{{"Experienta minima", "null"},
                                {"Experienta maxima", "null"}};
                    }

                    JTable jTable5 = new JTable(data5, column);
                    jTable5.setSize(new Dimension(JobRequest.getWidth(), 50));
                    jTable5.setEnabled(false);

                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    jTable5.getColumnModel().getColumn(0).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable5.getColumnModel().getColumn(1).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable5.setFillsViewportHeight(false);
                    JScrollPane jScrollPane5 = new JScrollPane(jTable5);
                    jScrollPane5.setPreferredSize(new Dimension(jTable5.getWidth(), 3 * 20));
                    JobRequest.add(jScrollPane5, JobConstraints);

                    JButton MedieNecesara = new JButton("CONSTRANGERE MEDIE");
                    MedieNecesara.setEnabled(true);
                    MedieNecesara.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    MedieNecesara.setForeground(Color.BLACK);
                    MedieNecesara.setBackground(Color.BLUE);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(MedieNecesara, JobConstraints);

                    String[][] data6 = {{"Medie minima", String.valueOf(Double.valueOf(request.getKey().medieAcademica.inferior).longValue())},
                            {"Medie maxima", String.valueOf(Double.valueOf(request.getKey().medieAcademica.superior).longValue())}};
                    if (request.getKey().medieAcademica.inferior == -1) {
                        data6 = new String[][]{{"Medie minima", "null"},
                                {"Medie maxima", String.valueOf(Double.valueOf(request.getKey().medieAcademica.superior).longValue())}};
                    }
                    if (request.getKey().medieAcademica.superior == 3000) {
                        data6 = new String[][]{{"Medie minima", String.valueOf(Double.valueOf(request.getKey().medieAcademica.inferior).longValue())},
                                {"Medie maxima", "null"}};
                    }
                    if (request.getKey().medieAcademica.inferior == -1 && request.getKey().medieAcademica.superior == 3000) {
                        data6 = new String[][]{{"Medie minima", "null"},
                                {"Medie maxima", "null"}};
                    }

                    JTable jTable6 = new JTable(data6, column);
                    jTable6.setSize(new Dimension(JobRequest.getWidth(), 50));
                    jTable6.setEnabled(false);

                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    jTable6.getColumnModel().getColumn(0).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable6.getColumnModel().getColumn(1).setPreferredWidth(JobRequest.getWidth() / 2);
                    jTable6.setFillsViewportHeight(false);
                    JScrollPane jScrollPane6 = new JScrollPane(jTable6);
                    jScrollPane6.setPreferredSize(new Dimension(jTable6.getWidth(), 3 * 20));
                    JobRequest.add(jScrollPane6, JobConstraints);

                    JButton Salariu = new JButton("Salariu");
                    Salariu.setEnabled(true);
                    Salariu.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    Salariu.setForeground(Color.BLACK);
                    Salariu.setBackground(Color.GREEN);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(Salariu, JobConstraints);

                    JButton Money = new JButton(String.valueOf(Double.valueOf(request.getKey().salary)));
                    Money.setEnabled(true);
                    Money.setPreferredSize(new Dimension(JobRequest.getWidth(), 25));
                    Money.setForeground(Color.BLACK);
                    Money.setBackground(Color.GREEN);
                    JobConstraints.gridy = contorJob;
                    contorJob++;
                    JobRequest.add(Money, JobConstraints);

                    JobConstraints.weightx = 1;
                    JobConstraints.weighty = 1;
                    JobRequest.add(new JLabel(" "), JobConstraints);
                    JobRequest.setVisible(true);
                    pack();
                }
            });

            Requests.add(jButton, bagConstraints);
            contor++;
            i++;
        }

        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        Requests.add(new JLabel(" "), bagConstraints);
        Requests.setVisible(true);
        pack();
    }

    public void ButtonSearchAction() {
        //Creeaza panel-ul de profil al unui user, in profilePage.
        String name1 = CautareUsers.getText();
        String[] parts = name1.split(" ");
        System.out.println(name1);

        Application application = Application.getInstance();
        boolean gasit = false;
        if (!resizable) {
            Dimension dimension = new Dimension(TabelPanel.getWidth(), TabelPanel.getHeight());
            ScrollTabelPanel.setPreferredSize(dimension);
            resizable = true;
        }
        TabelPanel.removeAll();
        TabelPanel.setVisible(true);
        pack();
        int contor = 0;
        for (User user : application.Users) {
            if (name1.compareTo(user.cv.information.getNume() + " " + user.cv.information.getPrenume()) == 0 ||
                    name1.compareTo(user.cv.information.getPrenume() + " " + user.cv.information.getNume()) == 0) {
                gasit = true;
                System.out.println(user);
                JButton nume = new JButton(user.cv.information.getNume() + " " + user.cv.information.getPrenume());
                nume.setEnabled(true);
                nume.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                nume.setForeground(Color.BLACK);
                nume.setBackground(Color.GREEN);

                GridBagLayout gridBagLayout = new GridBagLayout();
                TabelPanel.setLayout(gridBagLayout);
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.gridx = 0;
                constraints.anchor = GridBagConstraints.NORTH;

                constraints.gridy = contor;
                contor++;
                TabelPanel.add(nume, constraints);

                JButton information = new JButton("Information");
                information.setEnabled(true);
                information.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                information.setForeground(Color.BLACK);
                information.setBackground(Color.YELLOW);
                constraints.gridy = contor;
                contor++;
                TabelPanel.add(information, constraints);

                String[][] data = {{"Nume", user.cv.information.getNume()},
                        {"Prenume", user.cv.information.getPrenume()},
                        {"Sex", user.cv.information.getSex()},
                        {"Data de nastere", user.cv.information.getData_de_nastere()},
                        {"Telefon", user.cv.information.getTelefon()},
                        {"Email", user.cv.information.getEmail()}};

                String[] column = new String[]{"About", "Date"};
                JTable jTable = new JTable(data, column);
                jTable.setSize(new Dimension(TabelPanel.getWidth(), 50));
                jTable.setEnabled(false);

                constraints.gridy = contor;
                contor++;
                jTable.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                jTable.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                jTable.setFillsViewportHeight(false);
                JScrollPane jScrollPane = new JScrollPane(jTable);
                jScrollPane.setPreferredSize(new Dimension(TabelPanel.getWidth(), (7) * jTable.getRowHeight()));
                if (jScrollPane.isWheelScrollingEnabled()) {
                    jScrollPane.setPreferredSize(new Dimension(TabelPanel.getWidth(), (7) * jTable.getRowHeight() + 11));
                }
                TabelPanel.add(jScrollPane, constraints);

                JButton limbi = new JButton("Limbi");
                limbi.setEnabled(true);
                limbi.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                limbi.setForeground(Color.BLACK);
                limbi.setBackground(Color.DARK_GRAY);
                constraints.gridy = contor;
                contor++;
                TabelPanel.add(limbi, constraints);

                String[] column2 = new String[]{"Limba", "Nivel"};
                String[][] data2 = new String[user.cv.information.getLanguages().size()][2];
                int i = 0;
                for (Limbi language : user.cv.information.getLanguages()) {
                    data2[i][0] = language.getLimba();
                    data2[i][1] = language.getLevel();
                    i++;
                }
                JTable jTable2 = new JTable(data2, column2);
                jTable2.setSize(new Dimension(TabelPanel.getWidth(), 50));
                jTable2.setEnabled(false);
                constraints.gridy = contor;
                contor++;
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                jTable2.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                jTable2.setFillsViewportHeight(false);
                JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                jScrollPane2.setPreferredSize(new Dimension(TabelPanel.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight()));
                if (jScrollPane2.isWheelScrollingEnabled()) {
                    jScrollPane2.setPreferredSize(new Dimension(TabelPanel.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight() + 11));
                }
                TabelPanel.add(jScrollPane2, constraints);

                JButton Educations = new JButton("Educations");
                Educations.setEnabled(true);
                Educations.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                Educations.setForeground(Color.BLACK);
                Educations.setBackground(Color.BLUE);
                constraints.gridy = contor;
                contor++;
                TabelPanel.add(Educations, constraints);

                for (Education education : user.cv.educations) {
                    JButton buttons = new JButton();
                    if (education.end != null) {
                        buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                education.end.get(Calendar.DATE) + "." + education.end.get(Calendar.MONTH) + "." + education.end.get(Calendar.YEAR));
                    } else {
                        buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                "now");
                    }
                    buttons.setEnabled(true);
                    buttons.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    buttons.setForeground(Color.BLACK);
                    buttons.setBackground(Color.RED);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(buttons, constraints);

                    String[][] data3 = {{"Level", education.level},
                            {"Institutie", education.institutie},
                            {"Medie", education.medie.toString()}};

                    JTable jTable3 = new JTable(data3, column);
                    jTable3.setEnabled(false);
                    jTable3.setSize(new Dimension(TabelPanel.getWidth(), 50));
                    constraints.gridy = contor;
                    contor++;
                    jTable3.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable3.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable3.setFillsViewportHeight(false);
                    JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                    jScrollPane3.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable3.getRowHeight())));
                    if (jScrollPane3.isWheelScrollingEnabled()) {
                        jScrollPane3.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable3.getRowHeight() + 11)));
                    }
                    TabelPanel.add(jScrollPane3, constraints);
                }

                JButton Experiences = new JButton("Experiences");
                Experiences.setEnabled(true);
                Experiences.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                Experiences.setForeground(Color.BLACK);
                Experiences.setBackground(Color.BLUE);
                constraints.gridy = contor;
                contor++;
                TabelPanel.add(Experiences, constraints);

                for (Experience experience : user.cv.experiences) {
                    JButton buttonsExperience = new JButton();
                    if (experience.end != null) {
                        buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                experience.end.get(Calendar.DATE) + "." + experience.end.get(Calendar.MONTH) + "." + experience.end.get(Calendar.YEAR));
                    } else {
                        buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                "now");
                    }
                    buttonsExperience.setEnabled(true);
                    buttonsExperience.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    buttonsExperience.setForeground(Color.BLACK);
                    buttonsExperience.setBackground(Color.RED);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(buttonsExperience, constraints);

                    String[][] data4 = {{"Company", experience.company},
                            {"Departament", experience.department},
                            {"Pozitie", experience.pozitie}};

                    JTable jTable4 = new JTable(data4, column);
                    jTable4.setSize(new Dimension(TabelPanel.getWidth(), 50));
                    constraints.gridy = contor;
                    contor++;
                    jTable4.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable4.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable4.setFillsViewportHeight(false);
                    jTable4.setEnabled(false);
                    JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                    jScrollPane4.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable4.getRowHeight())));
                    if (jScrollPane4.isWheelScrollingEnabled()) {
                        jScrollPane4.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable4.getRowHeight() + 11)));
                    }
                    TabelPanel.add(jScrollPane4, constraints);
                }

                constraints.weightx = 1;
                constraints.weighty = 1;
                TabelPanel.add(new JLabel(" "), constraints);

                TabelPanel.setVisible(true);
                pack();
                break;
            }
            for (String name : parts) {
                if (user.cv.information.getNume().compareTo(name) == 0 || user.cv.information.getPrenume().compareTo(name) == 0) {
                    System.out.println(user);
                    JButton nume = new JButton(user.cv.information.getNume() + " " + user.cv.information.getPrenume());
                    nume.setEnabled(true);
                    nume.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                    nume.setForeground(Color.BLACK);
                    nume.setBackground(Color.GREEN);

                    GridBagLayout gridBagLayout = new GridBagLayout();
                    TabelPanel.setLayout(gridBagLayout);
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.gridx = 0;
                    constraints.anchor = GridBagConstraints.NORTH;

                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(nume, constraints);

                    JButton information = new JButton("Information");
                    information.setEnabled(true);
                    information.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    information.setForeground(Color.BLACK);
                    information.setBackground(Color.YELLOW);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(information, constraints);

                    String[][] data = {{"Nume", user.cv.information.getNume()},
                            {"Prenume", user.cv.information.getPrenume()},
                            {"Sex", user.cv.information.getSex()},
                            {"Data de nastere", user.cv.information.getData_de_nastere()},
                            {"Telefon", user.cv.information.getTelefon()},
                            {"Email", user.cv.information.getEmail()}};

                    String[] column = new String[]{"About", "Date"};
                    JTable jTable = new JTable(data, column);
                    jTable.setSize(new Dimension(TabelPanel.getWidth(), 50));
                    jTable.setEnabled(false);

                    constraints.gridy = contor;
                    contor++;
                    jTable.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable.setFillsViewportHeight(false);
                    JScrollPane jScrollPane = new JScrollPane(jTable);
                    jScrollPane.setPreferredSize(new Dimension(TabelPanel.getWidth(), (7) * jTable.getRowHeight()));
                    if (jScrollPane.isWheelScrollingEnabled()) {
                        jScrollPane.setPreferredSize(new Dimension(TabelPanel.getWidth(), (7) * jTable.getRowHeight() + 11));
                    }
                    TabelPanel.add(jScrollPane, constraints);

                    JButton limbi = new JButton("Limbi");
                    limbi.setEnabled(true);
                    limbi.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    limbi.setForeground(Color.BLACK);
                    limbi.setBackground(Color.DARK_GRAY);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(limbi, constraints);

                    String[] column2 = new String[]{"Limba", "Nivel"};
                    String[][] data2 = new String[user.cv.information.getLanguages().size()][2];
                    int i = 0;
                    for (Limbi language : user.cv.information.getLanguages()) {
                        data2[i][0] = language.getLimba();
                        data2[i][1] = language.getLevel();
                        i++;
                    }
                    JTable jTable2 = new JTable(data2, column2);
                    jTable2.setSize(new Dimension(TabelPanel.getWidth(), 50));
                    jTable2.setEnabled(false);
                    constraints.gridy = contor;
                    contor++;
                    jTable2.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable2.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                    jTable2.setFillsViewportHeight(false);
                    JScrollPane jScrollPane2 = new JScrollPane(jTable2);
                    jScrollPane2.setPreferredSize(new Dimension(TabelPanel.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight()));
                    if (jScrollPane2.isWheelScrollingEnabled()) {
                        jScrollPane2.setPreferredSize(new Dimension(TabelPanel.getWidth(), (user.cv.information.getLanguages().size() + 1) * jTable2.getRowHeight() + 11));
                    }
                    TabelPanel.add(jScrollPane2, constraints);

                    JButton Educations = new JButton("Educations");
                    Educations.setEnabled(true);
                    Educations.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    Educations.setForeground(Color.BLACK);
                    Educations.setBackground(Color.BLUE);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(Educations, constraints);

                    for (Education education : user.cv.educations) {
                        JButton buttons = new JButton();
                        if (education.end != null) {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    education.end.get(Calendar.DATE) + "." + education.end.get(Calendar.MONTH) + "." + education.end.get(Calendar.YEAR));
                        } else {
                            buttons.setText(education.start.get(Calendar.DATE) + "." + education.start.get(Calendar.MONTH) + "." + education.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttons.setEnabled(true);
                        buttons.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                        buttons.setForeground(Color.BLACK);
                        buttons.setBackground(Color.RED);
                        constraints.gridy = contor;
                        contor++;
                        TabelPanel.add(buttons, constraints);

                        String[][] data3 = {{"Level", education.level},
                                {"Institutie", education.institutie},
                                {"Medie", education.medie.toString()}};

                        JTable jTable3 = new JTable(data3, column);
                        jTable3.setEnabled(false);
                        jTable3.setSize(new Dimension(TabelPanel.getWidth(), 50));
                        constraints.gridy = contor;
                        contor++;
                        jTable3.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                        jTable3.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                        jTable3.setFillsViewportHeight(false);
                        JScrollPane jScrollPane3 = new JScrollPane(jTable3);
                        jScrollPane3.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable3.getRowHeight())));
                        if (jScrollPane3.isWheelScrollingEnabled()) {
                            jScrollPane3.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable3.getRowHeight() + 11)));
                        }
                        TabelPanel.add(jScrollPane3, constraints);
                    }

                    JButton Experiences = new JButton("Experiences");
                    Experiences.setEnabled(true);
                    Experiences.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                    Experiences.setForeground(Color.BLACK);
                    Experiences.setBackground(Color.BLUE);
                    constraints.gridy = contor;
                    contor++;
                    TabelPanel.add(Experiences, constraints);

                    for (Experience experience : user.cv.experiences) {
                        JButton buttonsExperience = new JButton();
                        if (experience.end != null) {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    experience.end.get(Calendar.DATE) + "." + experience.end.get(Calendar.MONTH) + "." + experience.end.get(Calendar.YEAR));
                        } else {
                            buttonsExperience.setText(experience.start.get(Calendar.DATE) + "." + experience.start.get(Calendar.MONTH) + "." + experience.start.get(Calendar.YEAR) + " - " +
                                    "now");
                        }
                        buttonsExperience.setEnabled(true);
                        buttonsExperience.setPreferredSize(new Dimension(TabelPanel.getWidth(), 25));
                        buttonsExperience.setForeground(Color.BLACK);
                        buttonsExperience.setBackground(Color.RED);
                        constraints.gridy = contor;
                        contor++;
                        TabelPanel.add(buttonsExperience, constraints);

                        String[][] data4 = {{"Company", experience.company},
                                {"Departament", experience.department},
                                {"Pozitie", experience.pozitie}};

                        JTable jTable4 = new JTable(data4, column);
                        jTable4.setSize(new Dimension(TabelPanel.getWidth(), 50));
                        constraints.gridy = contor;
                        contor++;
                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(TabelPanel.getWidth() / 2);
                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(TabelPanel.getWidth() / 2);
                        jTable4.setFillsViewportHeight(false);
                        jTable4.setEnabled(false);
                        JScrollPane jScrollPane4 = new JScrollPane(jTable4);
                        jScrollPane4.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable4.getRowHeight())));
                        if (jScrollPane4.isWheelScrollingEnabled()) {
                            jScrollPane4.setPreferredSize(new Dimension(TabelPanel.getWidth(), (4 * jTable4.getRowHeight() + 11)));
                        }
                        TabelPanel.add(jScrollPane4, constraints);
                    }

                    constraints.weightx = 1;
                    constraints.weighty = 1;
                    TabelPanel.add(new JLabel(" "), constraints);

                    TabelPanel.setVisible(true);
                    pack();
                    gasit = true;
                    break;
                }
            }
        }
        if (!gasit) {
            System.out.println("User-ul cu numele: " + name1 + " nu exista!");
        }
    }
}

//Acestea sunt clase auxiliare in rezolvarea cerintelor.
class JButtonEdited {
    Request<Job, Consumer> request;
    JButton jButton;

    public JButtonEdited(Request<Job, Consumer> request, String name) {
        this.request = request;
        jButton = new JButton(name);
    }
}

class JButtonCompany {
    JButton jButton;
    Company company;

    public JButtonCompany(Company company, String name) {
        this.company = company;
        jButton = new JButton(name);
    }
}

class JButtonDepartment {
    JButton jButton;
    Departament departament;

    public JButtonDepartment(Departament departament, String name) {
        this.departament = departament;
        jButton = new JButton(name);
    }
}

class LoginPanel extends JPanel {
    //Am folosit aceasta clasa pentru a forma un Jpanel care sa aiba un design frumos si sa poata fi folosit personalizat in program in functie de tipul de login.
    JTextField username, password, usernameJLabel, passwordJLabel, hintUsername, hintPassword;
    JButton login, hint;
    JPanel userNamePanel, passWordPanel, buttons;

    public LoginPanel(String type, String hintUser, String hintPass) {
        this.setPreferredSize(new Dimension(525, 200));
        this.setMaximumSize(new Dimension(525, 200));
        this.setMinimumSize(new Dimension(525, 200));
        this.setBackground(new Color(27, 70, 75));

        login = new JButton("Login");
        login.setPreferredSize(new Dimension(120, 60));
        login.setFocusable(false);
        login.setEnabled(true);
        hint = new JButton("Hint");
        hint.setPreferredSize(new Dimension(120, 20));
        hint.setFocusable(false);
        hint.setEnabled(true);

        username = new JTextField(20);
        password = new JPasswordField(20);
        hintUsername = new JTextField("", 20);
        hintPassword = new JTextField("", 20);
        if ("User".equals(type)) {
            usernameJLabel = new JTextField("User Email", 20);
        } else if ("Admin".equals(type)) {
            usernameJLabel = new JTextField("Admin Username", 20);
        } else if ("Manager".equals(type)) {
            usernameJLabel = new JTextField("Manager", 20);
        }
        usernameJLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        usernameJLabel.setEditable(false);
        usernameJLabel.setHorizontalAlignment(JTextField.CENTER);
        passwordJLabel = new JTextField("Password", 20);
        passwordJLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        passwordJLabel.setEditable(false);
        passwordJLabel.setHorizontalAlignment(JTextField.CENTER);
        hintUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        hintUsername.setEditable(false);
        hintUsername.setHorizontalAlignment(JTextField.CENTER);
        hintUsername.setVisible(true);
        hintPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        hintPassword.setEditable(false);
        hintPassword.setHorizontalAlignment(JTextField.CENTER);
        hintPassword.setVisible(true);

        setLayout(new GridBagLayout());

        userNamePanel = new JPanel(new GridBagLayout());
        passWordPanel = new JPanel(new GridBagLayout());
        buttons = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        userNamePanel.add(usernameJLabel, constraints);
        buttons.add(login, constraints);
        passWordPanel.add(passwordJLabel, constraints);
        constraints.gridy++;
        buttons.add(hint, constraints);
        userNamePanel.add(username, constraints);
        passWordPanel.add(password, constraints);
        constraints.gridy++;
        userNamePanel.add(hintUsername, constraints);
        passWordPanel.add(hintPassword, constraints);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.NONE;
        this.add(userNamePanel, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 2;
        this.add(passWordPanel, constraints);
        constraints.gridy++;
        constraints.gridy++;
        constraints.gridx--;
        this.add(buttons, constraints);

        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hintUsername.setText(hintUser);
                hintPassword.setText(hintPass);
            }
        });
    }
}