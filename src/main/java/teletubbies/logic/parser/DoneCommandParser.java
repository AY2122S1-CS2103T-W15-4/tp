package teletubbies.logic.parser;

import static java.util.Objects.requireNonNull;

import teletubbies.commons.core.Messages;
import teletubbies.commons.core.index.Index;
import teletubbies.logic.commands.DoneCommand;
import teletubbies.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DoneCommand} object
 */
public class DoneCommandParser implements Parser<DoneCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DoneCommand}
     * and returns a {@code DoneCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DoneCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneCommand(index);
        } catch (ParseException parseException) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), parseException
            );
        }

    }
}