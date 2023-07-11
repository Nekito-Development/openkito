package wtf.norma.nekito.command.impl;

import org.apache.commons.lang3.StringUtils;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.value.Value;
import wtf.norma.nekito.module.value.impl.*;

import java.util.stream.Collectors;

@CommandInfo(
        alias = "value",
        usage = ".value <set/list/see> <module> [args]"
)
public class ValueCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length <= 1)
            throw new CommandException("Usage: &d" + getUsage());

        Module module = Nekito.INSTANCE.getModuleManager()
                .findModule(args[1])
                .orElseThrow(() -> new CommandException(String.format("&7Module &d%s &7not found.", args[1].toUpperCase())));

        switch (args[0].toLowerCase()) {
            case "list": {
                ChatHelper.printMessage("$p Module values: \n" +
                        module.getValues().values().stream()
                                .map(value -> " &8>> &7" + value.getName() + "&8(&f" + value.getClass().getSimpleName() + "&8)")
                                .collect(Collectors.joining("\n"))
                );
                break;
            }
            case "set": {
                if (args.length <= 3)
                    throw new CommandException("Usage: &d.value set " + module.getName() + " <value name> <value>");

                Value<?> value = Nekito.INSTANCE.getModuleManager().findValue(module, args[2])
                        .orElseThrow(() -> new CommandException(String.format("&7Value &d%s &7not found.", args[2].toUpperCase())));

                try {
                    if (value instanceof BooleanValue) {
                        ((BooleanValue) value).set(Boolean.parseBoolean(args[3]));
                    } else if (value instanceof ObjectValue) {
                        ((ObjectValue) value).set(args[3]);
                    } else if (value instanceof StringValue) {
                        ((StringValue) value).set(StringUtils.join(args, " ", 3, args.length));
                    } else if (value instanceof NumberValue) {
                        set((NumberValue<?>) value, args[3]);
                    } else if (value instanceof RangeNumberValue) {
                        ((RangeNumberValue) value).set(Float.parseFloat(args[3]));
                    }
                    ChatHelper.printMessage(String.format("Value &d%s&7: was set to &f%s", value.getName(), value));
                } catch (Exception e) {
                    throw new CommandException(e.getMessage());
                }

                break;
            }
            case "see": {
                if (args.length <= 2)
                    throw new CommandException("Usage: &d.value see " + module.getName() + " <value name>");

                Value<?> value = Nekito.INSTANCE.getModuleManager().findValue(module, args[2])
                        .orElseThrow(() -> new CommandException(String.format("&7Value &d%s &7not found.", args[2].toUpperCase())));
                ChatHelper.printMessage(String.format("Value &d%s&7: &f%s", value.getName(), value));
                break;
            }
            default:
                throw new CommandException("Usage: &d.value <set/list/see> <module>");
        }
    }

    //@TODO: Less ugly

    private void set(NumberValue<?> value, String string) {
        if (value.get() instanceof Byte) {
            ((NumberValue<Byte>) value).set(Byte.valueOf(string));
        } else if (value.get() instanceof Short) {
            ((NumberValue<Short>) value).set(Short.valueOf(string));
        } else if (value.get() instanceof Integer) {
            ((NumberValue<Integer>) value).set(Integer.valueOf(string));
        } else if (value.get() instanceof Long) {
            ((NumberValue<Long>) value).set(Long.valueOf(string));
        } else if (value.get() instanceof Float) {
            ((NumberValue<Float>) value).set(Float.valueOf(string));
        } else if (value.get() instanceof Double) {
            ((NumberValue<Double>) value).set(Double.valueOf(string));
        }
    }
}
