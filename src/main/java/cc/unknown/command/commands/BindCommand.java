package cc.unknown.command.commands;

import cc.unknown.Haru;
import cc.unknown.command.Command;
import cc.unknown.config.ClientConfig;
import cc.unknown.module.impl.Module;
import cc.unknown.utils.misc.KeybindUtil;

public class BindCommand extends Command {
    
	@Override
	public void onExecute(String[] args) {
	    if (args.length != 2) {
	    	this.sendChat(getColor("Red") + "Syntax Error.");
	        return;
	    }

	    String key = args[0];
	    String value = args[1];

	    if (key.equals(".")) {
	        key = "none";
	    }

	    Module mod = Haru.instance.getModuleManager().getModule(key);

	    if (mod != null) {
        	KeybindUtil.instance.bind(mod, KeybindUtil.instance.toInt(value));
        	this.sendChat(String.format("Bound %s to %s!", mod.getRegister().name(), value));
	        ClientConfig moduleConfig = new ClientConfig();
	        moduleConfig.saveConfig();
	    } else {
	    	this.sendChat(getColor("Red") + "Key or module §cwas not found!", value);
	    }
	}

	@Override
    public String getSyntax() {
        return ".bind <module> <key>";
    }

    @Override
    public String getDesc() {
        return "Sets binds for modules.";
    }

	@Override
	public String getName() {
		return "bind";
	}

	@Override
	public String getAlias() {
		return "b";
	}
}