<h1>Trello Issue Creator</h1>
<h2>Tech stack</h2>
<ul>
    <li>Java 8</li>
    <li>Maven 3.6</li>
    <li>Spring boot 3.6</li>
    <li>Cucumber</li>
    <li>Rest Assured</li>
    <li>Docker</li>
    <li>Makefiles</li>
</ul>
<h2>How to run</h2>
<p>In order to run you first have to obtain your Trello security token and key in order to access your account and
 boards. After that you have to retrieve the id of the board in which you wish to create new tickets.</p>
<p>Second you'll have to create the board in which you wish to create new issues, create the list "To Do" (in case it 
isn't already created) and create the labels "Research", "Maintenance", "Bug", "Test" and "Issue" verbatim or the app
 might not work as intended</p>
<p>With all this info retrieved, you may beging with the next step, running the application. You may choose to do it 
with docker or without it</p>
<h3>1- Docker</h3>
-- Working with version 19.03.12 - Other will most likely also work but no guarantees --

<p>Run these commands from the root replacing placeholders where necessary</p>
<ul>
    <li>make build (this will take a while)</li>
    <li>make run BOARD={BOARD} SECURITY_KEY={SECURITY_KEY} SECURITY_TOKEN={SECURITY_TOKEN}</li>
</ul>
<p>For Windows you either have to download Ubuntu's subsystems plugin or adapt and run docker's commands manually from 
the Makefile file adapting syntax where necessary</p>
<h3>2- Native</h3>
<p>Run these commands from the root replacing placeholders where necessary</p>
<ul>
    <li>make package</li>
    <li>make native make native BOARD={BOARD} SECURITY_KEY={SECURITY_KEY} SECURITY_TOKEN={SECURITY_TOKEN}</li>
</ul>
<p>For windows follow advice in the docker section.</p>
<h2>How to use</h2>
<p>This is a purely REST api without a GUI. To use this app, I provide you with a Postman Collection you can import into
your Postman and also pre-made Curls you can use and edit. These are located under trello-bridge/src directory.</p>
<p>Have fun!</p>