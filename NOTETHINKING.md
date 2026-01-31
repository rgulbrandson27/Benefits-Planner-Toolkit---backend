
### TO REVERSE
Commented out 'ORGANIZATION' entity key from client and employee - simplicity purposes - temp

### TO DOCUMENT
Added new integer field to Scenario entity for chart population

### TO-DO

### TO CONSIDER/ADDRESS
-Onset, entitlement, etc. may not fall within years listed - data there, just doesn't display - no action needed?

-Start year - may need to parse or change both to int

### TO REMEMBER

DATE FORMATTING
#### I wanted to use MM-DD-YYYY for SQL formatting - but....

###### YYYY-MM-DD
✅ International standard
✅ Unambiguous worldwide (no confusion about which number is month vs day)
✅ Sorts correctly (2025-01-15 comes before 2025-02-01)
✅ PostgreSQL expects this format
✅ Java LocalDate.parse() works without extra formatters
To format for display later if needed:
new Date('2020-03-15').toLocaleDateString('en-US') // "3/15/2020"


### TOO FUN TO FORGET:)

CLAUDE: "I genuinely "enjoy" working through these problems with you - "


