document.getElementById('login')
  .innerHTML='Welcome back!'
document.getElementById('defaultContent')
  .style.display='none';

CreateWindow(
  new NewWin(
    '1',612,115,615,260,
    'http://www.javaranch.com','JavaRanch'
  )
);
CreateWindow(
  new NewWin(
    '2',10,115,583,260,
    'http://www.google.com','Google'
  )
);
CreateWindow(
  new NewWin(
    '3',10,387,1220,300,
    'http://radio.javaranch.com/pascarello','Ajax Blog!'
  )
);
