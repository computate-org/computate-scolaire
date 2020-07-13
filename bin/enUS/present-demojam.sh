
function computate() {
#        echo "Do you want to execute the command below? "
        echo
#        echo "$1"
#        read -p '[s]kip, [q]uit ou [Enter] to continue: ' -i '' ACTION_COMMAND
        read -p '' -i '' ACTION_COMMAND
        case $ACTION_COMMAND in 
                [Ss]* );;
                [Qq]* ) exit 0 ;;
                * )
                eval "$1"
                ;;
        esac
}

computate "figlet -f slant 'Open';\
xdg-open 'https://books.google.com/books?id=D4gzDAAAQBAJ&pg=PA53&lpg=PA53&dq=Open+is+an+opportunity,+a+chance+to+broaden+the+mind,+free+tools+and+resources+to+benefit+all+of+mankind.+Open+is+an+aperture,+something+you+look+through,+access+for+all,+not+just+the+few.&source=bl&ots=6_mG4PsIK1&sig=ACfU3U3Dn2Qx7bZuuoN3tnwDCj8oFRQMPQ&hl=en&sa=X&ved=2ahUKEwiPqYbZnoPlAhU2CjQIHXgzBwEQ6AEwAXoECAkQAQ';\
echo 'Open is an opportunity, a chance to broaden the mind, free tools and resources to benefit all of mankind. Open is an aperture, something you look through, access for all, not just the few. '"

computate "echo 'My name is Christopher Tate and I love people, places and systems. '"

computate "echo 'I love to create systems for people to use in many places to solve things. '"
#computate "echo 'Systems that create relationships between things and with people in various places. '"
#computate "echo 'I even think about these systems while I daydream. '"

#computate "echo 'My name is Christopher Tate and I love people, places and things. '"

computate "xdg-open 'https://school.computate.org/api/school?rows=1&fl=classSimpleName';\
echo 'So imagine with me a school... '"

computate "xdg-open 'https://school.computate.org/api/school?fq=schoolLocation:Bountiful&fl=schoolLocation,classSimpleName';\
echo 'An amazing preschool in Bountiful Utah. '"

computate "xdg-open 'https://school.computate.org/api/school?fq=schoolLocation:Bountiful&fl=schoolName,schoolLocation,classSimpleName';\
echo 'An inviting place for kids eager to learn, called Little Orchard Preschool. '"

computate "xdg-open 'https://school.computate.org/api/school?fq=schoolLocation:Bountiful&fl=schoolAdministratorName,schoolName,schoolLocation,classSimpleName';\
echo 'With a charming founder named Nikita Orchard. '"

computate "xdg-open 'https://school.computate.org/api/year?fl=yearShortName,schoolCompleteName&fq=schoolLocation:Bountiful';\
echo 'Now this school goes back many years. '"

computate "xdg-open 'https://school.computate.org/api/year?fl=yearStart,schoolCompleteName&fq=yearStart:2017&fq=schoolLocation:Bountiful';\
echo 'Lets go back to the year 2017 where Nikita asked me if I could write school registration software better than her existing software. '"

computate "xdg-open 'https://school.computate.org/api/season?fl=seasonWinter,yearStart,yearShortName,schoolCompleteName&fq=yearStart:2017';\
echo 'During the winter season during the month of April, 2017. '"

computate "xdg-open 'http://localhost:8983/solr/scolaire/select?q=*:*&json.facet={'"'"'"'"'"'"'Number of enrollments in 2017'"'"'"'"'"'"':'"'"'"'"'"'"'unique(id)'"'"'"'"'"'"'}&rows=0&fq=yearStart_indexed_int:2017';\
echo 'I said, I'"'"'"'"'"'"'d love to help you with your site and write the software you need to accomplish your business goals, like increasing enrollments, which at that time was 231. '"

computate "xdg-open 'https://school.computate.org/api/school?fl=schoolName,schoolLocation,schoolCompleteName&fq=schoolLocation:Bountiful';\
echo 'So I set out to create software for Nikita to create records for her school... '"

computate "xdg-open 'https://school.computate.org/api/year?fl=yearStart,yearEnd,yearCompleteName&fq=yearStart:2017';\
echo 'Her current school year 2017 to 2018... '"

computate "xdg-open 'https://school.computate.org/api/season?fl=seasonStartDate,seasonWinter,seasonSummer,seasonCompleteName&fq=yearStart:2017';\
echo 'Her winter school season... '"

computate "xdg-open 'https://school.computate.org/api/session?fl=sessionStartDate,sessionEndDate,sessionCompleteName&fq=yearStart:2017';\
echo 'The session start and end date during that season... '"

computate "xdg-open 'https://school.computate.org/api/age?fl=ageStart,ageEnd,ageCompleteName&fq=yearStart:2017';\
echo 'The age groups in that session... '"

computate "xdg-open 'https://school.computate.org/api/block?fl=blockStartTime,blockEndTime,blockPricePerMonth,blockMonday,blockTuesday,blockWednesday,blockThursday,blockFriday,blockCompleteName&fq=yearStart:2017';\
echo 'And the school blocks for the start and end times, the days of the week and the price per month... '"

computate "figlet -f slant 'The API Secret';\
echo 'Now you probably didnt know that an API can tell stories, but it can! '"

computate "echo 'And I love telling stories, and now I will tell you the secret. '"

computate "xdg-open 'http://localhost:8983/solr/#/scolaire/query';\
echo 'Its an open source search engine! '"

#computate "xdg-open 'https://www.att.com/buy/phones/';\
#echo 'I learned about search engines to solve business problems while building the user interface for AT&T the year they launched the iPhone. '"

#computate "xdg-open 'https://computate.neocities.org/jpg/france.jpg';\
#echo 'I realized that search engines were so important, that I went on a pilgrimage to Bordeaux France with my family to learn more. '"

computate "xdg-open 'http://localhost:8983/solr/#/scolaire/query';\
echo 'Search engines are so powerful, that I also use a search engine to read and write my code for me. '"

computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc';\
echo 'In fact, for my open source projects, every piece of code I write, as soon as I save it, is immediately loaded into a search engine. '"

computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&fq=classeNomSimple_enUS_indexed_string:School';\
echo 'The reason for this, is that code describes systems and relationships, like school registration systems. '"

computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&fq=classeNomSimple_enUS_indexed_string:BankAdjustment';\
echo 'Or banking systems like the promotions and adjustments API which my team will be putting into production this weekend at Citi bank. In its earliest sprints, the entire promotions JavaEE project, was actually built by a search engine! From its swagger spec, to its request models, response models, common models, persistence models, and documentation, they were all built from search records about the Java class, field and method information, but most importantly, the code comments. '"

computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&json.facet={}&rows=0';\
echo 'As you can see here, there are over 10,000 very important chunks of code floating around my search engine here on my laptop. '"

#computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"'}&rows=0';\
#echo '500 of them are Java classes. '"

#computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"',partIsMethod:'"'"'"'"'"'"'sum(partEstMethode_indexed_boolean)'"'"'"'"'"'"'}&rows=0';\
#echo 'Over 5,000 of them are Java methods. '"

#computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"',partIsMethod:'"'"'"'"'"'"'sum(partEstMethode_indexed_boolean)'"'"'"'"'"'"',partIsField:'"'"'"'"'"'"'sum(partEstChamp_indexed_boolean)'"'"'"'"'"'"'}&rows=0';\
#echo 'Over 800 of them are Java fields. '"

#computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"',partIsMethod:'"'"'"'"'"'"'sum(partEstMethode_indexed_boolean)'"'"'"'"'"'"',partIsField:'"'"'"'"'"'"'sum(partEstChamp_indexed_boolean)'"'"'"'"'"'"',partIsEntity:'"'"'"'"'"'"'sum(partEstEntite_indexed_boolean)'"'"'"'"'"'"'}&rows=0';\
#echo 'And over 2,000 of them are a useful type of method, that I like to call an entity method. '"

#computate "xdg-open 'https://github.com/computate/computate-scolaire/blob/master/src/main/java/org/computate/scolaire/enUS/school/School.java';\
#echo 'An entity method, which is a protected void method that starts with an underscore, can be used to describe, define, initialize and relate a Java class in terms of the real world object that it represents. '"

computate "xdg-open 'https://github.com/computate/computate-scolaire/blob/master/src/main/java/org/computate/scolaire/enUS/school/School.java';\
echo 'When you think of a School in this Java class and in the real world, for example, think how the schoolKey relates to other yearKeys, seasonKeys, ageGroupKeys, blockKeys, enrollmentKeys and childKeys, like I searched for earlier in my school API. '"

computate "echo 'Also, how you can define a school by a schoolName, schoolPhoneNumber, schoolAdministratorName, schoolLocation, schoolAddress, or many other fields which may be stored in a database and updated over time. '"

computate "echo 'Think about how the way a school is defined, and the relations it has with other objects can affect other generated fields, like the schoolShortName, schoolCompleteName, schoolId and pageUrl for the school. '"

computate "xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&fq=classeNomSimple_enUS_indexed_string:School&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"',partIsMethod:'"'"'"'"'"'"'sum(partEstMethode_indexed_boolean)'"'"'"'"'"'"',partIsField:'"'"'"'"'"'"'sum(partEstChamp_indexed_boolean)'"'"'"'"'"'"',partIsEntity:'"'"'"'"'"'"'sum(partEstEntite_indexed_boolean)'"'"'"'"'"'"'}&rows=0';\
echo 'Now what if we put everything we know about this Java class into a search engine? '"

computate "echo 'This search engine API could tell more about this Java class than you and I could possibly describe on our own. '"

computate "figlet -f slant 'Code Translation';\
xdg-open 'https://github.com/computate/computate-scolaire/blob/master/src/main/java/org/computate/scolaire/frFR/ecole/Ecole.java';\
echo 'Now Ill tell you something surprising, this School.java class was actually written in French first, and translated by open source software into English as a second language to work in both languages! '"

computate "figlet -f slant 'Code APIs';\
xdg-open 'http://localhost:8983/solr/computate/select?q=*:*&fq=classeNomSimple_enUS_indexed_string:School&sort=modifiee_indexed_date%20desc&json.facet={partIsClass:'"'"'"'"'"'"'sum(partEstClasse_indexed_boolean)'"'"'"'"'"'"',partIsMethod:'"'"'"'"'"'"'sum(partEstMethode_indexed_boolean)'"'"'"'"'"'"',partIsField:'"'"'"'"'"'"'sum(partEstChamp_indexed_boolean)'"'"'"'"'"'"',partIsEntity:'"'"'"'"'"'"'sum(partEstEntite_indexed_boolean)'"'"'"'"'"'"'}';\
echo 'And the search engine can tell me about 1,297 facts about every individual piece of this Java class to build an API, a website, an OpenAPI swagger definition, getters and setters and so much more. '"

computate "xdg-open 'https://console.pro-us-east-1.openshift.com/console/project/computateorg/overview';\
echo 'There is too much too explain in an 8 minute demo, but it is deployed here on OpenShift.com, along with a Solr search engine, a Zookeeper cluster manager, computate.org which is my own website for teaching open source software development, also in multiple languages, Red Hat SSO, and a PostgreSQL database.  '"

computate "xdg-open 'https://raw.githubusercontent.com/computate/computate-scolaire/master/src/main/resources/openapi3-enUS.yaml';\
echo 'Lets quickly look at how the entire site is defined... '"

computate "xdg-open 'http://editor.swagger.io/';\
echo 'As an OpenAPI 3 Swagger spec, with endpoints to create, update and search for all objects in the site: payments, children, users, moms, dads, guardians, ages, enrollments, seasons, blocks, schools, years and sessions. '"

computate "xdg-open 'https://school.computate.org/school';\
echo 'Lets login through the Red Hat Single Sign on server and show you the site while we listen to Nikita explain how open source software allowed her business to grow. '"

computate "xdg-open /home/ctate/ctate-laptop/mp3/make-business-better-with-open-source-software.mp3;\
echo 'Yes, because with open source software, like in my situation, I expanded a second location, and I added the second location instantly, and another system may not have communicated well for me, super easy to grow. '"

computate "xdg-open 'https://scolaire-dev.computate.org:10180/ecole';\
echo 'Lets also checkout the site in French. '"

computate "xdg-open /home/ctate/ctate-laptop/mp3/business-growth-with-open-source-software.mp3;\
echo 'Because of online enrollment, people are techy, take a tour, talk about it, sign up right then and there. They can sign up online. They have come up a lot more fast then when they were hand filled in, hand typed, managers do not have to retype.'"

computate "xdg-open 'http://localhost:8983/solr/scolaire/select?q=*:*&json.facet={'"'"'"'"'"'"'Number of enrollments in 2017'"'"'"'"'"'"':'"'"'"'"'"'"'unique(id)'"'"'"'"'"'"'}&rows=0&fq=yearStart_indexed_int:2017';\
echo 'Now lets look how enrollments at little orchard preschool started in 2017 at  231... '"

computate "xdg-open 'http://localhost:8983/solr/scolaire/select?q=*:*&json.facet={'"'"'"'"'"'"'Number of enrollments in 2018'"'"'"'"'"'"':'"'"'"'"'"'"'unique(id)'"'"'"'"'"'"'}&rows=0&fq=yearStart_indexed_int:2018';\
echo 'Then in 2018, enrollments more than doubled to 502. '"

computate "xdg-open 'http://localhost:8983/solr/scolaire/select?q=*:*&json.facet={'"'"'"'"'"'"'Number of enrollments in 2019'"'"'"'"'"'"':'"'"'"'"'"'"'unique(id)'"'"'"'"'"'"'}&rows=0&fq=yearStart_indexed_int:2019';\
echo 'And now in 2019, after just 2 months in, enrollments exceeded that too, and are already up to 523! And that is the power of open source software in business! '"

computate "figlet -f slant 'Internationalization';\
xdg-open 'https://github.com/computate/computate-scolaire';\
echo 'Computate open source software builds software in multiple languages at the same time, making internationalization easy. Vert.x makes it scalable. OpenShift deploys it across the world, and that makes a big impact in communities large and small. '"

computate "figlet -f slant 'Computate!!!';\
echo 'You can make software that benefits your community and the world. Check out computate on github, on the Red Hat demo system, and computate.org. And dont give up on your dreams, you can do hard things. Computate. '"

