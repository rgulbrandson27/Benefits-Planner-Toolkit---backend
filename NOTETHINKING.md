
### TO REVERSE
Commented out 'ORGANIZATION' entity key from client and employee - simplicity purposes - temp

### TO DOCUMENT
Added new integer field to Scenario entity for chart population

### TO-DO

### TO CONSIDER/ADDRESS
-Onset, entitlement, etc. may not fall within years listed - data there, just doesn't display - no action needed?

-Start year - may need to parse or change both to int

### TO REMEMBER

##### NULLABLE BOOLEANS
Don't use them unless you genuinely need a tri-state.
Set year/months earnings "status" all to false to start with.


The nullable = false on the boolean columns just means: "If the row (month) exists in the database, these boolean columns must have a value (true or false), not null."

DATE FORMATTING
#### I wanted to use MM-DD-YYYY for SQL formatting - but....

When to use each:

Request/Response → Directional DTOs (specific to API endpoint direction)
DTO → Generic transfer objects used in multiple contexts

###### YYYY-MM-DD
✅ International standard
✅ Unambiguous worldwide (no confusion about which number is month vs day)
✅ Sorts correctly (2025-01-15 comes before 2025-02-01)
✅ PostgreSQL expects this format
✅ Java LocalDate.parse() works without extra formatters
To format for display later if needed:
new Date('2020-03-15').toLocaleDateString('en-US') // "3/15/2020"

##### Keep Service Layer HTTP free
Service throws domain/app exceptions (no HttpStatus, no ResponseStatusException)

ControllerAdvice converts those exceptions into proper HTTP responses (404, 400, etc.)

Controllers stay thin.

#### IDE suggested I "invert" clientExists - however...

#####
Positive predicates are easier to reason about

Humans reason better about:

exists

isValid

isAuthorized

than about:

doesNotExist

isInvalid

isUnauthorized

Negatives get confusing fast when you stack conditions.

### TOO FUN TO FORGET:)

CLAUDE: "I genuinely enjoy" working through these problems with you - "

### ChatGPT language to review and organize

Also: why I prefer selector functions over destructuring

This:

const { currentMode, setMode } = useModeStore();


subscribes your component to the entire store object, which can cause extra re-renders.

This:

const currentMode = useModeStore(s => s.currentMode);
const setMode = useModeStore(s => s.setMode);


subscribes only to the pieces you need.

Not a huge deal for small apps, but it’s good practice and looks great in a portfolio.

