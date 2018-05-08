package Homework6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User: blangel
 * Date: 10/11/17
 * Time: 8:04 AM
 */
public class EvilVillain implements InvocationHandler {

    private static interface CallingCard {

        Object callingCard();

    }

    private static class Encountered {

        private final AtomicBoolean encountered;

        public Encountered() {
            this.encountered = new AtomicBoolean(false);
        }

        private void markEncountered() {
            this.encountered.set(true);
        }

    }

    private static final String KABOOM = "\uD83D\uDCA3 \uD83D\uDCA5";

    private static final int MINIMUM_AMOUNT_WIRES = 2;

    private static final int MAXIMUM_AMOUNT_WIRES = 100;

    private static final int COUNT_DOWN_SECONDS = 60;

    private final ScheduledExecutorService executor;

    private final boolean free;

    private final Encountered red;

    private final Encountered blue;

    private final Map<Bomb, List<Wire>> bombs;

    private final Map<Bomb, Integer> bombNumbers;

    private final Map<Bomb, List<WireColor>> orderCuts;

    private final Map<Wire, Bomb> inverted;

    private final Map<Wire, WireColor> wires;

    private final Set<Wire> callingCards;

    public EvilVillain() {
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.free = true;
        this.red = new Encountered();
        this.blue = new Encountered();
        this.bombs = new HashMap<>();
        this.bombNumbers = new HashMap<>();
        this.orderCuts = new HashMap<>();
        this.inverted = new HashMap<>();
        this.wires = new HashMap<>();
        this.callingCards = new HashSet<>();
    }

    public Bomb terrorize() {
        Random random = hatchPlan();
        Bomb bomb = (Bomb) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { Bomb.class }, this);
        List<Wire> wires = createWires(bomb, random);
        bombs.put(bomb, wires);
        bombNumbers.put(bomb, bombs.size());
        countDown(bomb, 0);
        return bomb;
    }

    private void countDown(final Bomb bomb, int retries) {
        if (retries >= COUNT_DOWN_SECONDS) {
        		System.out.println("retries>60,ignite bomb...");
            igniteBomb(bomb);
            System.exit(1);
        }
        String terminal = System.getenv("TERM");
        final String terminalBold = ("xterm".equals(terminal) ? "1" : "0");
        final int bombNumber = this.bombNumbers.get(bomb);
        executor.schedule(new Runnable() {
            @Override public void run() {
                if (orderCuts.get(bomb).isEmpty()) {
                    System.out.printf("\u001b[%s;32mBomb %d defused!\u001b[0m%n", terminalBold, bombNumber);
                } else {
                    String color = (retries > (COUNT_DOWN_SECONDS - 10)) ? "31" : "33";
                    System.out.printf("\u001b[%s;%smBomb %d timer at %d\u001b[0m%n", terminalBold, color, bombNumber, (COUNT_DOWN_SECONDS - retries));
                    countDown(bomb, (retries + 1));
                }
            }
        }, 1L, TimeUnit.SECONDS);
    }

    private List<Wire> createWires(Bomb bomb, Random random) {
        int wireAmount = MINIMUM_AMOUNT_WIRES + (random.nextInt(MAXIMUM_AMOUNT_WIRES - MINIMUM_AMOUNT_WIRES));
        List<Wire> wires = new ArrayList<>(wireAmount);
        for (int i = 0; i < wireAmount; i++) {
            Wire wire = (Wire) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { Wire.class, Colored.class, CallingCard.class }, this);
            inverted.put(wire, bomb);
            wires.add(wire);
        }
        Wire chosen = wires.get(random.nextInt(wires.size()));
        callingCards.add(chosen);

        int stop = Math.min(wireAmount, (MINIMUM_AMOUNT_WIRES + random.nextInt(2)));
        for (int i = 0; i < stop; i++) {
            Wire wire = wires.get(i);
            WireColor color = (random.nextBoolean() ? WireColor.Red : WireColor.Blue);
            if (i == 0) {
                if (color == WireColor.Red) {
                    red.markEncountered();
                } else if (color == WireColor.Blue) {
                    blue.markEncountered();
                } else {
                    throw new AssertionError();
                }//means the evil guy cannot be caught until he make both 2 kinds of bomb
            }
            this.wires.put(wire, color);
        }
        WireColor[] colors = new WireColor[] { WireColor.Black, WireColor.White, WireColor.Green };
        for (int i = stop; i < wires.size(); i++) {
            Wire wire = wires.get(i);
            WireColor color = colors[random.nextInt(colors.length)];
            this.wires.put(wire, color);
        }
        Collections.shuffle(wires);
        List<WireColor> orderedCuts = new ArrayList<>(stop);
        for (Wire wire : wires) {
            WireColor color = this.wires.get(wire);
            if ((color == WireColor.Blue) || (color == WireColor.Red)) {
                orderedCuts.add(color);
            }
        }
        this.orderCuts.put(bomb, orderedCuts);
        return wires;
    }

    private Random hatchPlan() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(4L));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        return new Random();
    }

    public boolean notCaptured() {
        return (free || !red.encountered.get() || !blue.encountered.get());
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("hashCode".equals(method.getName())) {
            return System.identityHashCode(proxy);
        }
        if ("equals".equals(method.getName())) {
            return (proxy == args[0]);
        }
        if ("callingCard".equals(method.getName())) {
            if (callingCards.contains(proxy)) {
                return this;
            }
            return proxy;
        }
        if ((proxy instanceof Bomb) && !bombs.containsKey(proxy)) {
            tamperingDetected("fake bomb");
            System.exit(1);
        }
        if ((proxy instanceof Wire) && !wires.containsKey(proxy)) {
            tamperingDetected("fake wire");
            System.exit(1);
        }
        if (proxy instanceof Bomb) {
            return invokeBomb((Bomb) proxy, method, args);
        }
        if (proxy instanceof Wire) {
            return invokeWire((Wire) proxy, method, args);
        }
        return null;
    }

    private Object invokeBomb(Bomb proxy, Method method, Object[] args) throws Throwable {
        if ("getWires".equals(method.getName())) {
            List<Wire> wires = this.bombs.get(proxy);
            return wires.toArray(new Wire[wires.size()]);
        }
        if ("getBombNumber".equals(method.getName())) {
            return bombNumbers.get(proxy);
        }
        tamperingDetected("invalid bomb function");
        System.exit(1);
        return null;
    }

    private void tamperingDetected(String reason) {
        String cause = getTamperingCause(new RuntimeException().getStackTrace());
        StringBuilder bombs = new StringBuilder();
        for (Bomb bomb : this.bombs.keySet()) {
            bombs.append(String.format("%s", getBombIgnitionText(bomb)));
        }
        System.out.printf("%n\u001b[1;33mTampering detected \u001b[0m[ %s @ %s ]\u001b[1;33m; igniting all bombs \u001b[0m%n%s%n", reason, cause, bombs.toString());
    }

    private String getTamperingCause(StackTraceElement[] stackTraceElements) {
        if ((stackTraceElements == null) || (stackTraceElements.length < 1)) {
            return "<unknown>";
        }
        for (int i = 0; i < stackTraceElements.length; i++) {
            String element = stackTraceElements[i].toString();
            if ((element != null) && !element.contains("EvilVillain") && !element.contains("homework6.Toolbox.")
                    && !element.contains(".proxy.")) {
                return element;
            }
        }
        return stackTraceElements[0].toString();
    }

    private Object invokeWire(Wire proxy, Method method, Object[] args) throws Throwable {
        Bomb bomb = inverted.get(proxy);
        if (bomb == null) {
        		System.out.println("invokeWire bombl==null,ignite bomb...");
            igniteBomb(bomb);
            System.exit(1);
        }
        int number = getBombNumber(bomb);
        if ("cut".equals(method.getName())) {
            WireColor color = wires.get(proxy);
            System.out.printf("  cutting %s wire on bomb %d%n", color.name().toLowerCase(), number);
            if ((color != WireColor.Blue) && (color != WireColor.Red)) {
            		System.out.println("invokeWire wire not blue or red,ignite bomb...");
                igniteBomb(bomb);
                System.exit(1);
            }
            List<WireColor> orderCuts = this.orderCuts.get(bomb);
            //add bymyself,to check the orderCuts' colors
            int size = orderCuts.size();
            System.out.println("the orderCuts colors:");
            for(int i=0;i<size;i++) {
            		System.out.println(orderCuts.get(i));
            }
            System.out.println("orderCuts over");
            if (orderCuts.get(0) != color) {
            		System.out.println("invokeWire color not in ordercuts,ignite bomb...");
                igniteBomb(bomb);
                System.exit(1);
            }
            orderCuts.remove(0);
            return null;
        }
        if ("getColor".equals(method.getName())) {
            WireColor color = wires.get(proxy);
            if (color == null) {
            		System.out.println("getcolor not found,ignite bomb...");
                igniteBomb(bomb);
                System.exit(1);
            }
            return color;
        }
        tamperingDetected("invalid wire function");
        System.exit(1);
        return null;
    }

    private void igniteBomb(Bomb bomb) {
        System.out.printf("%s", getBombIgnitionText(bomb));
    }

    private String getBombIgnitionText(Bomb bomb) {
        int number = getBombNumber(bomb);
        return String.format("\u001b[1;31mBomb %d igniting...\u001b[0m%s%n", number, KABOOM);
    }

    private int getBombNumber(Bomb bomb) {
        Integer number = bombNumbers.get(bomb);
        if (number == null) {
            tamperingDetected("fake bomb");
            System.exit(1);
        }
        return number;
    }
}
