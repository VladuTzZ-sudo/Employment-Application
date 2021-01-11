package com.TEMA;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class GUIInterface extends JFrame {
    private JPanel HomePage;
    private JPanel ManagerPage;
    private JPanel ProfilePage;
    private JButton adminPageButton;
    private JButton managerPageButton;
    private JButton profilePageButton;
    private JButton backButton1;
    private JButton backButton2;
    private JPanel MainPage;
    private JPanel APage;
    private JButton backbutton3;
    private JButton ButtonSearchUsers;
    private JTextField CautareUsers;
    private JPanel TabelPanel;
    private JScrollPane ScrollTabelPanel;
    private JSplitPane ManagerPane;
    private JButton ManagerBackButton;
    private JButton backButton;
    private String companyName = null;
    private JPanel Requests;
    private JPanel UserRequest;
    private JPanel JobRequest;
    private JTabbedPane jTabbedPane;
    private JScrollPane jScrollPaneForRequest;
    private JButton AcceptButton;
    private JButton CancelButton;
    private boolean resizable = false;
    private JButtonEdited AccesedRequest;

    public GUIInterface(String titlu) {
        super(titlu);
        this.setContentPane(HomePage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        adminPageButton.setPreferredSize(new Dimension(300, 150));
        managerPageButton.setPreferredSize(new Dimension(300, 150));
        profilePageButton.setPreferredSize(new Dimension(300, 150));

        setVisible(true);
        pack();
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
                setContentPane(APage);
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
        backbutton3.addActionListener(new ActionListener() {
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

        ButtonSearchUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonSearchAction();
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
        //AcceptButton.setForeground(Color.GREEN);
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    companyName = companyFinder.getText();
                    Application application = Application.getInstance();
                    if (application.getCompany(companyName) != null) {
                        ProcessButton.setEnabled(true);
                        jTabbedPane.setSelectedIndex(0);
                        showRequests();
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
                        jTabbedPane.setSelectedIndex(0);
                        ProcessButton.setEnabled(true);
                        showRequests();
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

        jScrollPaneForRequest = new JScrollPane(jTabbedPane);
        jScrollPaneForRequest.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneForRequest.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Requests.setPreferredSize(companynameSplit.getRightComponent().getPreferredSize());
        companynameSplit.setLeftComponent(PanelCompanieSearch);
        companynameSplit.setRightComponent(jScrollPaneForRequest);

        splitPane.setRightComponent(companynameSplit);
        ManagerPane.setRightComponent(splitPane);
        ManagerPane.setEnabled(false);

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
                    ProcessButton.setEnabled(true);
                    jTabbedPane.setSelectedIndex(0);
                    showRequests();
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
        for (Request<Job, Consumer> request : manager.requests) {
            bagConstraints.gridy = contor;
            JLabel labeltest = new JLabel(request.getKey().name);
            JButtonEdited jButtonEdited;
            jButtonEdited = new JButtonEdited(request, request.getValue1().cv.information.getNume() + " " + request.getValue1().cv.information.getPrenume());
            JButton jButton = jButtonEdited.jButton;
            jButton.setHorizontalTextPosition(SwingConstants.CENTER);
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
                    if (request.getKey().anAbsolvire.superior == 3000){
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
                    if (request.getKey().aniExperienta.superior == 3000){
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
                    if (request.getKey().medieAcademica.superior == 3000){
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

    /*
    public void ButtonSearchAction() {
        String name1 = CautareUsers.getText();
        String[] parts = name1.split(" ");
        System.out.println(name1);
        Application application = Application.getInstance();
        boolean gasit = false;
        TabelPanel.removeAll();
        for (User user : application.Users) {
            if (name1.compareTo(user.cv.information.getNume() + " " + user.cv.information.getPrenume()) == 0 ||
                    name1.compareTo(user.cv.information.getPrenume() + " " + user.cv.information.getNume()) == 0) {
                gasit = true;
                System.out.println(user);
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

                    BoxLayout boxLayout = new BoxLayout(TabelPanel, BoxLayout.Y_AXIS);
                    TabelPanel.setLayout(boxLayout);
                    TabelPanel.add(nume, BorderLayout.CENTER);

                    JButton information = new JButton("Information");
                    information.setEnabled(true);
                    information.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                    information.setForeground(Color.BLACK);
                    information.setBackground(Color.YELLOW);
                    TabelPanel.add(information, BorderLayout.CENTER);

                    String data[][] = {{"Nume", user.cv.information.getNume()},
                            {"Prenume", user.cv.information.getPrenume()},
                            {"Sex", user.cv.information.getSex()},
                            {"Data de nastere", user.cv.information.getData_de_nastere()},
                            {"Telefon", user.cv.information.getTelefon()},
                            {"Email", user.cv.information.getEmail()}};

                    String column[] = new String[]{"About", "Date"};
                    JTable jTable = new JTable(data, column);
                    jTable.setSize(new Dimension(Integer.MAX_VALUE,50));



                    TabelPanel.add(new JScrollPane(jTable), BorderLayout.CENTER);

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
    */
    public void ButtonSearchAction() {
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
        JobRequest.removeAll();
        UserRequest.removeAll();
        Requests.removeAll();
        int contor = 0;
        for (User user : application.Users) {
            if (name1.compareTo(user.cv.information.getNume() + " " + user.cv.information.getPrenume()) == 0 ||
                    name1.compareTo(user.cv.information.getPrenume() + " " + user.cv.information.getNume()) == 0) {
                gasit = true;
                System.out.println(user);
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
                    jScrollPane.setPreferredSize(new Dimension(TabelPanel.getWidth(), 6 * 21));
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

class JButtonEdited extends JButton {
    Request<Job, Consumer> request;
    JButton jButton;

    public JButtonEdited(Request<Job, Consumer> request, String name) {
        this.request = request;
        jButton = new JButton(name);
    }
}
