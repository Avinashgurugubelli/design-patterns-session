# Composite pattern

- In software engineering, the composite pattern is a partitioning design pattern. The composite pattern describes a group of objects that are treated the same way as a single instance of the same type of object.
- The intent of a composite is to "compose/create" objects into tree structures to represent part-whole hierarchies. 
- Implementing the composite pattern lets clients treat individual objects and compositions uniformly.

```
A Real Life Example: Group Messaging
    Say, There are six people on my contact list:
     1. Mom, 2. Dad, 
     3. Uncle Bob, 4. Cousin Nick, 5. Aunt Julia,
     6. Amy jackson. 
    The Parents group is composed of 
        -Mom and Dad, 
    whereas the Uncle Bob’s Family group is composed of 
        - Uncle Bob, Cousin Nick, and Aunt Julia.
    
                                    Contacts
                                    --------
                                        |
            -----------------------------------------------------------------
            |                           |                                    |
          Parents                   Uncle Bob’s Family                      Amy jacksom
            |                           |                                     
        -----------                ---------------------------------
        |           |              |              |                 |
        MOM        DAD            Uncle Bob      Cousin Nick        Aunt Julia

```

- In Above example, Contacts is represented in a tree structure made of nodes. A node is either a group of people or a single person.
- If a node is a group of people, like Parents, then it contains other nodes. If a node is a single person, like Mom, then it’s a leaf node.
- For example, I can add another node, CollegeFriends, containing four friends, two of which are grouped into the Roommates node.