identify a pattern from those that we have already studied that you think is most appropriate and provide a detailed explain of your choice and how it improves your program.

 names the pattern and explains why you chose it

Identify a pattern:
Iterator/Composite Pattern

Why?
The first pattern I have decided to implement into this code is the Composite Pattern, also known as the Iterator pattern. In comparison to prior learnt patterns, the Composite Pattern is the best pattern suited to refractor the the code as it overall increases efficiency and flexibility, as well as allowing for encapsulation and decoupling that additionally helps improve the code. In terms of efficiency and flexibility, the Composite pattern amplifies these factors through a form of decoupling, removing "for loops" which increases to create type specific iterators which remove redudant data that the for loop instantiate along with normal iterators. The allowance of these type iterators intertwine more into the flexiblity side of the Composite pattern which allows for these type iterators. This means that the Composite pattern has allowed the code to create direct pathways between classes and function calls using the specific type iterator to remove redudant data from and the iterator interface alongside increasing the response time. Another way in which the code benefits from the Composite pattern is that it adds the functionality of encapsulation. This allows the code to store variables in a private matter, specific to a class, which allows only the necessary code to be displayed and therefore also improves redundancy. Overall, the Composite Pattern is utilised in this code to be able to remove the redundancy and increase efficiency by using enapsulation and decoupling. 

<!-- -Flexible, by making it an interface since an interface only stores method names and not functionality (Functionality = More efficient path since your narrowing down code to suit the fuctionality vs making code to suit everything) -->
<!-- -Encapsulation, storing variables in a private matter (Specific to a class), encapsulates redundant data and use only whats needed (Private variables to help redudnat code) -->
<!-- -Decoupling = Making specific iterators (Now won't have to worry about what implementation, can always use the same interface "Iterator" to iterate over items -> Become decoupled from the implementation) -->

How?
<!-- -By separating iterator into class specific iterators so you narrow it and increase the response time + readability, redundant data
is removed from the code -->