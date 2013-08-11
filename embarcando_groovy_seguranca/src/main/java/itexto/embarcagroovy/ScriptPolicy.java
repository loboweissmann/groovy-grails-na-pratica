package itexto.embarcagroovy;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.util.HashSet;
import java.util.Set;



public final class ScriptPolicy extends Policy {
	
	private final Set<URL> locations = new HashSet<URL>();
	
	public ScriptPolicy() {
		try {
			locations.add(new URL("file", "", "/groovy/shell"));
			locations.add(new URL("file", "", "/groovy/script"));
			
		} catch (MalformedURLException e) {
			
		}
	}
	
	public PermissionCollection getPermissions(CodeSource codesource) {
        PermissionCollection perms = new Permissions();
        if (!locations.contains(codesource.getLocation())) {
            perms.add(new AllPermission());
        }
        return perms;
    }
	
}
